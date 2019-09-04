package net.haesleinhuepf.clij.painting;

import net.haesleinhuepf.clij.CLIJ;
import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij.coremem.enums.NativeTypeEnum;
import net.haesleinhuepf.clijx.CLIJx;
import org.junit.Test;

public class DrawLineTest {
    @Test
    public void test2d() {
        CLIJx CLIJx = CLIJx.getInstance();
        ClearCLBuffer image = CLIJx.create(new long[]{100, 100}, NativeTypeEnum.Float);

        DrawLine.drawLine(CLIJ.getInstance(), image, 10f, 20f, 0f, 50f, 50f, 0f, 10f);
        //clijx.op.drawLine(image, 10f, 10f, 0f, 10f, 50f, 0f, 5f);

        //new ImageJ();
        //clijx.show(image, "image");
        //new WaitForUserDialog("helo").show();
        image.close();
    }
    @Test
    public void test3d() {
        CLIJx CLIJx = CLIJx.getInstance();
        ClearCLBuffer image = CLIJx.create(new long[]{100, 100, 100}, NativeTypeEnum.Float);

        DrawLine.drawLine(CLIJ.getInstance(), image, 10f, 20f, 0f, 50f, 50f, 70f, 10f);
        //clijx.op.drawLine(image, 10f, 10f, 0f, 10f, 50f, 0f, 5f);

        //new ImageJ();
        //clijx.show(image, "image");
        //new WaitForUserDialog("helo").show();
        image.close();
    }

}