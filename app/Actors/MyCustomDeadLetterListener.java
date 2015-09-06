package Actors;

import akka.actor.DeadLetter;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * Created by ahmad on 06/09/15.
 */
public class MyCustomDeadLetterListener extends UntypedActor {
    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof DeadLetter) {
            log.info("FROM CUSTOM LISTENER: " + message.toString());
        }
    }
}
