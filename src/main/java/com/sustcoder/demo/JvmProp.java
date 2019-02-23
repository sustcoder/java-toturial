package com.sustcoder.demo;

import com.sun.management.VMOption;
import sun.management.HotSpotDiagnostic;

import java.util.List;

/**
 * 通过Java代码调用该API得到所有可动态修改的JVM参数
 */
public class JvmProp {
    public static void main(String[] args) {
        HotSpotDiagnostic mxBean=new HotSpotDiagnostic();
        List<VMOption> vmOptions=mxBean.getDiagnosticOptions();
        for (VMOption option:vmOptions ) {
            System.out.println(option.getName()+" = "+option.getValue());
        }
    }
}
