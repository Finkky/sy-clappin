package cz.cvut.fit.culkajac.dp.services.store.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Predicate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.switchyard.component.camel.Route;

import cz.cvut.fit.culkajac.dp.services.store.StoreAggregator;

@Route(StoreAggregator.class)
public class StoreAggregatorBuilder extends RouteBuilder {

	private final static String SERVICE_NAME = StoreAggregator.class.getSimpleName();

	private AggregationStrategy aggStrat;
	private Predicate completionPred, finalizer;

	public StoreAggregatorBuilder() {

		this.aggStrat = new AggregationStrategy() {
			@SuppressWarnings("unchecked")
			@Override
			public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {

				Object newBody = newExchange.getIn().getBody();
				if (oldExchange == null) {
					if (!(newBody instanceof Collection)) {
						List<Object> l = new ArrayList<Object>();
						l.add(newBody);
						newExchange.getIn().setBody(l);
					}
					newExchange.setOut(newExchange.getIn());
					return newExchange;
				} else {
					Object oldBody = oldExchange.getIn().getBody();

					if (oldBody instanceof Collection && newBody instanceof Collection) {
						((Collection<Object>) oldBody).addAll((Collection<Object>) newBody);
					} else if (oldBody instanceof Collection) {
						((Collection<Object>) oldBody).add(newBody);
					} else if (newBody instanceof Collection) {
						((Collection<Object>) newBody).add(oldBody);
						oldExchange.getIn().setBody(newBody);
					} else {
						List<Object> l = new ArrayList<Object>();
						l.add(newBody);
						l.add(oldBody);
						oldExchange.getIn().setBody(l);
					}
					oldExchange.setOut(oldExchange.getIn());
					return oldExchange;

				}
			}
		};

		this.completionPred = new Predicate() {
			@Override
			public boolean matches(Exchange exchange) {

				int expectedCount = exchange.getIn().getHeader("destinationRoutes", Collection.class).size();

				if (exchange.getIn().getBody() instanceof Collection) {
					int inExchangeMessageCount = exchange.getIn().getBody(Collection.class).size();

					return inExchangeMessageCount == expectedCount;
				}

				return expectedCount == 1;
			}
		};
		this.finalizer = new Predicate() {
			@SuppressWarnings("unchecked")
			@Override
			public boolean matches(Exchange exchange) {

				exchange.getIn().getHeader("out", Collection.class).addAll((Collection<Object>) exchange.getIn().getBody());
				return true;

			}
		};
	}

	@Override
	public void configure() {
		from("switchyard://" + SERVICE_NAME).aggregate(this.aggStrat).header("processId")
				.completionPredicate(this.completionPred).completionTimeout(5000).validate(this.finalizer);
	}
}
