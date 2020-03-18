package com.edu.zju.culture.mbg.service.impl;

import com.edu.zju.culture.common.ImageSimilarity;
import com.edu.zju.culture.mbg.service.CheckRelicService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class CheckRelicServiceImpl implements CheckRelicService {
    @Override
    public String checkRelic(File f1, File f2) {
        try {
            int similarity = ImageSimilarity.getHammingDistance(f1 ,f2);
            if(similarity > 0.95)
                return "鉴定为真";
            return "鉴定为假";
        } catch (IOException e) {
            return "上传出错";
        }
    }
}
