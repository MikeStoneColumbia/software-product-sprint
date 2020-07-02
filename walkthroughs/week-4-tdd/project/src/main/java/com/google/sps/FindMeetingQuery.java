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
import java.util.Collection;

public final class FindMeetingQuery {
  public Collection<TimeRange> query(Collection<Event> events, MeetingRequest request) {
    //throw new UnsupportedOperationException("TODO: Implement this method.");
  
        List<TimeRange> results = new LinkedList<>();
        ArrayList<Event> exisitngEvents = new ArrayList<>();


        if(request.getDuration() > TimeRange.WHOLE_DAY.duration()) // can schedule beyond 24 hours
            return Arrays.asList();

        if(request.getAttendees().size() == 0){ // if there are no attendees

            return Arrays.asList(TimeRange.WHOLE_DAY);
        }
 
        events.forEach(e -> exisitngEvents.add((Event) e ));
      //First we see if there is an overlap at the beg of the day.
      //there is then we start after the overlap.
      //there is no overlap start at the beginning.
        
        TimeRange eventTimeRange = exisitngEvents.get(0).getWhen();

        if(!eventTimeRange.overlaps(TimeRange.fromStartEnd(TimeRange.WHOLE_DAY.start(),eventTimeRange.start(),false))){

            //System.out.println("This finally works");
            results.add(TimeRange.fromStartEnd(TimeRange.WHOLE_DAY.start(),eventTimeRange.start(),false));

        }

        results.add(TimeRange.fromStartEnd(eventTimeRange.end(),TimeRange.WHOLE_DAY.end(),false));
        return results;
  
  }
}
