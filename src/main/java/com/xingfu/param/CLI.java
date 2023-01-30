package com.xingfu.param;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.xingfu.utils.MD5Util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * @author wz
 * @version 1.0
 * @date 2023-01-29 16:39
 * @description
 */
public class CLI {
    @Parameter(names = "-input", description = "original photo location:path", required = true, order = 0)
    private String inputPath;

    @Parameter(names = "-output", description = "md5 picture location:path", required = true, order = 1)
    private String outputPath;

    @Parameter(names = "-ext", description = "md5 picture extension", order = 2)
    private String ext = "jpg";

    @Parameter(names = "--help", help = true, order = 3)
    private boolean help;

    @Parameter(names = "--version", description = "photo-md5 tool version", help = true, order = 4)
    private boolean v;

    public void run(JCommander jCommander) {
        if (help) {
            jCommander.setProgramName("java -cp GenPhotoMd5-1.0-SNAPSHOT-jar-with-dependencies.jar com.xingfu.App");
            jCommander.usage();
        } else if (v) {
            JCommander.getConsole().println("1.0");
        } else {
            File dirInput = new File(inputPath);
            File dirOutput = new File(outputPath);
            if (!dirOutput.exists()) {
                boolean bool = dirOutput.mkdirs();
                if (!bool) {
                    JCommander.getConsole().println("mkdir output error");
                    return;
                }
            }
            if (dirInput.isDirectory() && dirInput.exists()) {
                getDirectory(dirInput);
            } else {
                JCommander.getConsole().println("please check input,error:input is not directory or exist");
            }
        }
    }

    /**
     * 递归遍历文件夹
     *
     * @param file photo路径
     */
    private void getDirectory(File file) {
        File[] flist = file.listFiles();
        if (flist == null || flist.length == 0) {
            JCommander.getConsole().println("please check input,error:input don't have photo");
            return;
        }
        for (File f : flist) {
            if (f.isDirectory()) {
                getDirectory(f);
            } else {
                try {
                    copyPic(f, outputPath, ext);
                } catch (IOException e) {
                    JCommander.getConsole().println("exception:" + e.fillInStackTrace().toString());
                    return;
                }
            }
        }
    }

    /**
     * 复制图片并重命名md5
     *
     * @param pic        photo
     * @param outputPath 输出目录
     * @param ext        输出图片扩展名
     * @throws IOException
     */
    private void copyPic(File pic, String outputPath, String ext) throws IOException {
        //图片校验
        if(isPicture(pic)){
            String md5 = MD5Util.getMD5(pic);
            File dest = new File(outputPath + File.separator + md5 + "." + ext);
            //如果存在相同md5值的则跳过
            if (!dest.exists()) {
                Files.copy(pic.toPath(), dest.toPath());
            }
            //输出图片全路径和md5的映射关系到控制台，“,”隔开
            String line = pic.getAbsolutePath() + "," + md5;
            JCommander.getConsole().println(line);
        } else {
            JCommander.getConsole().println(pic.getAbsolutePath()+" is not picture");
        }
    }

    /**
     * 校验文件是否为图片
     *
     * @param file 文件
     * @return
     */
    private boolean isPicture(File file){
        //TODO:校验图片逻辑
        return true;
    }
}
