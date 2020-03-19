package com.edu.zju.culture.mbg.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edu.zju.culture.common.DataGridView;
import com.edu.zju.culture.mbg.entity.Apply;
import com.edu.zju.culture.mbg.entity.Relic;
import com.edu.zju.culture.mbg.service.CheckRelicService;
import com.edu.zju.culture.mbg.service.IRelicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author y4oung
 * @since 2020-03-16
 */
@RestController
@RequestMapping("/relic")
public class RelicController {
    @Autowired
    CheckRelicService checkRelicService;

    @Autowired
    IRelicService iRelicService;

    @RequestMapping(value = "/upload/checkRelic", method = {RequestMethod.POST})
    public DataGridView headImg(@RequestParam(value = "file", required = false) MultipartFile file, @RequestParam(value = "id") String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String prefix = "";
        String dateStr = "";
        OutputStream out = null;
        InputStream fileInput = null;
        String input = "";
        try {
            if (file != null) {
                String originalName = file.getOriginalFilename();
                prefix = originalName.substring(originalName.lastIndexOf(".") + 1);
                dateStr = UUID.randomUUID().toString();
                String filepath = request.getServletContext().getRealPath("/static/") + dateStr + "." + prefix;
                filepath = filepath.replace("\\", "/");
                input = filepath;
                File files = new File(filepath);
                //打印查看上传路径
                System.out.println(filepath);
                if (!files.getParentFile().exists()) {
                    files.getParentFile().mkdirs();
                }
                file.transferTo(files);
            }
        } catch (Exception e) {
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (fileInput != null) {
                    fileInput.close();
                }
            } catch (IOException e) {
            }
        }
        DataGridView dataGridView = new DataGridView();
        Map<String, Object> data = new HashMap<>();
        data.put("src", "../../../static" + dateStr + "." + prefix);
        Relic relic = iRelicService.getById(id);
        File f1 = new File(input);
        File f2 = new File("C:/Users/Administrator/Desktop" + relic.getPicture());
        data.put("similarity", checkRelicService.checkRelic(f1, f2));
        dataGridView.setData(data);
        return dataGridView;
    }

    @RequestMapping(value = "/list")
    public DataGridView selectAll(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int limit) {
        IPage<Relic> relicIPage = new Page<>(page, limit);
        QueryWrapper<Relic> queryWrapper = new QueryWrapper<>();
        this.iRelicService.page(relicIPage, queryWrapper);
        return new DataGridView(relicIPage.getTotal(), relicIPage.getRecords());
    }
}

