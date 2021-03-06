package com.hdw.job.controller;

import com.hdw.common.result.CommonResult;
import com.hdw.common.result.PageParam;
import com.hdw.common.validator.ValidatorUtils;
import com.hdw.job.entity.ScheduleJobEntity;
import com.hdw.job.param.JobParam;
import com.hdw.job.service.IScheduleJobService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description 定时任务
 * @Author TuMinglong
 * @Date 2019/1/18 15:59
 **/
@Api(value = "定时任务接口", tags = {"定时任务接口"})
@RestController
@RequestMapping("/sys/schedule")
public class ScheduleJobController {
    @Autowired
    private IScheduleJobService scheduleJobService;

    /**
     * 定时任务列表
     */
    @ApiOperation(value = "定时任务列表", notes = "定时任务列表")
    @GetMapping("/list")
    @RequiresPermissions("sys/schedule/list")
    public CommonResult<PageParam<ScheduleJobEntity>> list(JobParam jobParam) {
        PageParam<ScheduleJobEntity> page = scheduleJobService.queryPage(jobParam);

        return CommonResult.ok().data(page);
    }

    /**
     * 定时任务信息
     */
    @ApiOperation(value = "定时任务日志信息", notes = "定时任务日志信息")
    @ApiImplicitParam(paramType = "path", name = "jobId", value = "主键ID", dataType = "Integer", required = true)
    @GetMapping("/info/{jobId}")
    @RequiresPermissions("sys/schedule/info")
    public CommonResult<ScheduleJobEntity> info(@PathVariable("jobId") Long jobId) {
        ScheduleJobEntity schedule = scheduleJobService.getById(jobId);

        return CommonResult.ok().data(schedule);
    }

    /**
     * 保存定时任务信息
     */
    @ApiOperation(value = "保存定时任务信息", notes = "保存定时任务信息")
    @PostMapping("/save")
    @RequiresPermissions("sys/schedule/save")
    public CommonResult save(@RequestBody ScheduleJobEntity scheduleJob) {
        ValidatorUtils.validateEntity(scheduleJob);

        scheduleJobService.insert(scheduleJob);

        return CommonResult.ok();
    }

    /**
     * 修改定时任务信息
     */
    @ApiOperation(value = "修改定时任务信息", notes = "修改定时任务信息")
    @PostMapping("/update")
    @RequiresPermissions("sys/schedule/update")
    public CommonResult update(@RequestBody ScheduleJobEntity scheduleJob) {
        ValidatorUtils.validateEntity(scheduleJob);

        scheduleJobService.update(scheduleJob);

        return CommonResult.ok();
    }

    /**
     * 删除定时任务信息
     */
    @ApiOperation(value = "删除定时任务信息", notes = "删除定时任务信息")
    @ApiImplicitParam(paramType = "query", name = "jobIds", value = "主键ID数组", dataType = "Integer", required = true, allowMultiple = true)
    @PostMapping("/delete")
    @RequiresPermissions("sys/schedule/delete")
    public CommonResult delete(@RequestBody Long[] jobIds) {
        scheduleJobService.deleteBatch(jobIds);

        return CommonResult.ok();
    }

    /**
     * 立即执行任务信息
     */
    @ApiOperation(value = "立即执行任务信息", notes = "立即执行任务信息")
    @ApiImplicitParam(paramType = "query", name = "jobIds", value = "主键ID数组", dataType = "Integer", required = true, allowMultiple = true)
    @PostMapping("/run")
    @RequiresPermissions("sys/schedule/run")
    public CommonResult run(@RequestBody Long[] jobIds) {
        scheduleJobService.run(jobIds);

        return CommonResult.ok();
    }

    /**
     * 暂停定时任务信息
     */
    @ApiOperation(value = "暂停定时任务信息", notes = "暂停定时任务信息")
    @ApiImplicitParam(paramType = "query", name = "jobIds", value = "主键ID数组", dataType = "Integer", required = true, allowMultiple = true)
    @PostMapping("/pause")
    @RequiresPermissions("sys/schedule/pause")
    public CommonResult pause(@RequestBody Long[] jobIds) {
        scheduleJobService.pause(jobIds);

        return CommonResult.ok();
    }

    /**
     * 恢复定时任务信息
     */
    @ApiOperation(value = "恢复定时任务信息", notes = "恢复定时任务信息")
    @ApiImplicitParam(paramType = "query", name = "jobIds", value = "主键ID数组", dataType = "Integer", required = true, allowMultiple = true)
    @PostMapping("/resume")
    @RequiresPermissions("sys/schedule/resume")
    public CommonResult resume(@RequestBody Long[] jobIds) {
        scheduleJobService.resume(jobIds);

        return CommonResult.ok();
    }

}
