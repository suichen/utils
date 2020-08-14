package com.suichen.utils.project.cicada;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.crossoverjie.cicada.server.action.WorkAction;
import top.crossoverjie.cicada.server.action.param.Param;
import top.crossoverjie.cicada.server.action.res.WorkRes;
import top.crossoverjie.cicada.server.annotation.CicadaAction;
import top.crossoverjie.cicada.server.context.CicadaContext;
import top.crossoverjie.cicada.server.enums.StatusEnum;

import java.util.concurrent.atomic.AtomicLong;

@CicadaAction(value = "demoAction")
public class DemoAction implements WorkAction {
    private static final Logger LOGGER = LoggerFactory.getLogger(DemoAction.class);
    private static AtomicLong index = new AtomicLong();

    @Override
    public void execute(CicadaContext context, Param param) throws Exception {
        String name = param.getString("name");
        Integer id = param.getInteger("id");

        LOGGER.info("name=[{}], id=[{}]", name, id);
        DemoResVO demoResVO = new DemoResVO();
        demoResVO.setIndex(index.incrementAndGet());
        WorkRes<DemoResVO> res = new WorkRes<>();
        res.setCode(StatusEnum.SUCCESS.getCode());
        res.setMessage(StatusEnum.SUCCESS.getMessage());
        res.setDataBody(demoResVO);
    }
}
