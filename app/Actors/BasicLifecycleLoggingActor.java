package Actors;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * Created by ahmad on 05/09/15.
 */
public class BasicLifecycleLoggingActor extends UntypedActor {

    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    public BasicLifecycleLoggingActor() {
        log.info("Inside BasicLifecycleLoggingActor Constructor");
        log.info(getContext().self().toString());
    }

    @Override
    public void preStart() throws Exception {
        log.info("Inside the preStart method of BasicLifecycleLoggingActor");
    }

    @Override
    public void onReceive(Object message) throws Exception {
        if (message.toString().contentEquals("hello")) {
            log.info("Got hello");
        } else if (message.toString().contentEquals("stop")) {
            getContext().stop(getSelf());
        }
    }

    @Override
    public void postStop() throws Exception {
        log.info("Inside postStop method of BasicLifecycleLoggingActor");
    }
}
