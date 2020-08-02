// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps;

import java.util.*;

public final class FindMeetingQuery {
  public Collection<TimeRange> query(Collection<Event> events, MeetingRequest request) {
    //throw new UnsupportedOperationException("TODO: Implement this method.");
  
        List<TimeRange> results = new LinkedList<>();
        ArrayList<Event> exisitingEvents = new ArrayList<>();

        if(request.getDuration() > TimeRange.WHOLE_DAY.duration()) // can't schedule beyond 24 hours
            return Arrays.asList();

        if(request.getAttendees().size() == 0){ // if there are no attendees

            return Arrays.asList(TimeRange.WHOLE_DAY);
        }
 
        events.forEach(e -> exisitingEvents.add((Event) e ));
      //First we see if there is an overlap at the beg of the day.
      //there is then we start after the overlap.
      //there is no overlap start at the beginning.
        
        TimeRange eventTimeRange = exisitingEvents.get(0).getWhen();

        //checking to see if there is any overlap in the beginning of the day
        if(!eventTimeRange.overlaps(TimeRange.fromStartEnd(TimeRange.WHOLE_DAY.start(),eventTimeRange.start(),false))){

            results.add(TimeRange.fromStartEnd(TimeRange.WHOLE_DAY.start(),eventTimeRange.start(),false));

        }

       // results.add(TimeRange.fromStartEnd(eventTimeRange.end(),TimeRange.WHOLE_DAY.end(),false));

        for(int i = 0; i < exisitingEvents.size()-1; i++){

            TimeRange event1 = exisitingEvents.get(i).getWhen();
            TimeRange event2 = exisitingEvents.get(i+1).getWhen();

            if(!event1.overlaps(event2)){ // check to see if 2 events overlap.

                if(event2.end() - event1.start() >= request.getDuration()) // check to see if there is enough time inbetween meetings
                    results.add(TimeRange.fromStartEnd(event1.end(),event2.start(),false));

            }

        }

        eventTimeRange = exisitingEvents.get(exisitingEvents.size()-1).getWhen();

        if(1440 - eventTimeRange.end() >= request.getDuration()) // check for time after the final event
            results.add(TimeRange.fromStartEnd(eventTimeRange.end(),1440,false));

        return results;
  
  }
}
