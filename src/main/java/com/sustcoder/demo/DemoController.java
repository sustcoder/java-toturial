package com.sustcoder.demo;

import com.sustcoder.algorithm.BitMap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
// http://yuanfentiank789.github.io/2016/07/12/outofmemory/
@Controller
@RequestMapping("/")
public class DemoController {

    final static BitMap bitMap=new BitMap(Integer.MAX_VALUE);
    final  static Map m=new ConcurrentHashMap();

    @RequestMapping(value = "/setBitMap")
    @ResponseBody
    public Model setBitMap(Model model, HttpServletRequest request){

        model.addAttribute("succ","true");
        Runtime rt = Runtime.getRuntime();
        System.out.println("totalM:"+(rt.totalMemory()>>20)+",freeM:"+(rt.freeMemory()>>20)+",used:"+((rt.totalMemory()-rt.freeMemory())>>20));
        for (int i = 0; i <Integer.MAX_VALUE ; i++) {
            bitMap.setOne(i);
        }
        System.out.println("totalM:"+(rt.totalMemory()>>20)+",freeM:"+(rt.freeMemory()>>20)+",used:"+((rt.totalMemory()-rt.freeMemory())>>20));
        System.out.println("-----------------------------------------");
        return model;
    }

    @RequestMapping(value = "/setByte")
    @ResponseBody
    public Model setByte(Model model,Integer size, HttpServletRequest request){
        Runtime rt = Runtime.getRuntime();
        System.out.println("init put  totalM:"+(rt.totalMemory()>>20)+",freeM:"+(rt.freeMemory()>>20)+",used:"+((rt.totalMemory()-rt.freeMemory())>>20));
        if( (rt.freeMemory()/1000/1000) <= size ){
            m.put(size,1);
            System.gc();
            for (int i = 0; i <Integer.MAX_VALUE ; i++) {
               if(bitMap.getN(i)==0){
                   System.out.println(i+"bit is wrong");
               }
            }
            System.out.println("after gc  totalM:"+(rt.totalMemory()>>20)+",freeM:"+(rt.freeMemory()>>20)+",used:"+((rt.totalMemory()-rt.freeMemory())>>20));
        }else{
            System.out.println("before put totalM:"+(rt.totalMemory()>>20)+",freeM:"+(rt.freeMemory()>>20)+",used:"+((rt.totalMemory()-rt.freeMemory())>>20));
        }
        m.put(size,new byte[1024*1024*size]);
        model.addAttribute("s",bitMap.getN(size));
        System.out.println("after put totalM:"+(rt.totalMemory()>>20)+",freeM:"+(rt.freeMemory()>>20)+",used:"+((rt.totalMemory()-rt.freeMemory())>>20));

        System.out.println("-----------------------------------------");
        return model;
    }

}
