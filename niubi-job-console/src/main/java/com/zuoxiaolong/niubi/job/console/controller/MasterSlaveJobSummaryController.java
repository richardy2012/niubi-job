/*
 * Copyright 2002-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.zuoxiaolong.niubi.job.console.controller;

import com.zuoxiaolong.niubi.job.persistent.entity.MasterSlaveJobSummary;
import com.zuoxiaolong.niubi.job.service.MasterSlaveJobService;
import com.zuoxiaolong.niubi.job.service.MasterSlaveJobSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Xiaolong Zuo
 * @since 1/15/2016 12:23
 */
@Controller
@RequestMapping("/masterSlaveJobSummaries")
public class MasterSlaveJobSummaryController extends AbstractController {

    @Autowired
    private MasterSlaveJobSummaryService masterSlaveJobSummaryService;

    @Autowired
    private MasterSlaveJobService masterSlaveJobService;

    @RequestMapping(value = "")
    public String list(Model model) {
        model.addAttribute("jobSummaries", masterSlaveJobSummaryService.getAllJobSummaries());
        return "master_slave_job_summary_list";
    }

    @RequestMapping(value = "/{id}")
    public String input(@PathVariable String id, Model model) {
        MasterSlaveJobSummary standbyJobSummary = masterSlaveJobSummaryService.getJobSummary(id);
        model.addAttribute("jobSummary", standbyJobSummary);
        model.addAttribute("jarFileNameList", masterSlaveJobService.getJarFileNameList(standbyJobSummary.getGroupName(), standbyJobSummary.getJobName()));
        return "master_slave_job_summary_input";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(MasterSlaveJobSummary masterSlaveJobSummary) {
        masterSlaveJobSummaryService.saveJobSummary(masterSlaveJobSummary);
        return success("/masterSlaveJobSummaries");
    }

}
