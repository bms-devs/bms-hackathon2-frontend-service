package org.bmshackathon.client;

import org.bmshackathon.video.VideoImage;

public interface VideoImageClient {
    VideoImage findOne(Long id);
}
