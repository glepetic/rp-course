package com.rp.scratch.course.sec09;

import com.rp.scratch.course.sec09.helper.EventStreamer;
import com.rp.scratch.util.ScratchUtil;

public class Lec02OverlapAndDrop {

    private static final EventStreamer eventStreamer = EventStreamer.instance();

    public static void main(String[] args) {

        /* second parameter = skip -> decides how many items you overlap (maxSize - skip = overlapCount)
           When maxSize < skip -> OVERLAP
           When maxSize == skip -> it's the same as using buffer to only provide the maxSize parameter (no overlap/no drop)
           When maxSize > skip -> DROP extra values (skip - maxSize = extraValues)
         */
        eventStreamer.eventStream(300)
                .buffer(3, 1)
                .subscribe(ScratchUtil.subscriber());

        ScratchUtil.sleepSeconds(60);

    }

}
