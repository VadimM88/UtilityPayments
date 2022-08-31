package com.example.legalreportviewer;

import com.example.legalreportviewer.entity.LegalReportBean;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Aspects {
    private final ConfigurableApplicationContext ctx;

    @Autowired
    public Aspects(ConfigurableApplicationContext ctx) {
        this.ctx = ctx;
    }

    @After(value = "execution(* com.example.legalreportviewer.ReportStorage.add(" +
                                "com.example.legalreportviewer.entity.LegalReportBean, boolean))")
    public void writeReportAdvice(JoinPoint jp){
        if((boolean)jp.getArgs()[1]) {
            ReportStorage bean = ctx.getBean(ReportStorage.class);
            bean.writeReport((LegalReportBean) jp.getArgs()[0]);
        }
    }
}
