package Actors;

import Protocols.TeacherProtocol;
import akka.actor.UntypedActor;

import java.util.Random;

/**
 * Created by ahmad on 31/08/15.
 */
public class TeacherActor extends UntypedActor {

    String[] quotes = {"Moderation is for cowards",
            "Anything worth doing is worth overdoing",
            "The trouble is you think you have time",
            "You never gonna know if you never even try"};

    @Override
    public void onReceive(Object message) throws Exception {

        if (message instanceof TeacherProtocol.QuoteRequest){
            //Get a random Quote from the list and construct a response
            TeacherProtocol.QuoteResponse quoteResponse
                    = new TeacherProtocol.QuoteResponse(quotes[new Random().nextInt(quotes.length)]);
            sender().tell(quoteResponse, self());
        }
    }
}
