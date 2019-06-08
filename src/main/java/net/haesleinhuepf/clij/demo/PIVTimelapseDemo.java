package net.haesleinhuepf.clij.demo;

import ij.IJ;
import ij.ImageJ;
import ij.ImagePlus;
import net.haesleinhuepf.clij.CLIJ;
import net.haesleinhuepf.clij.advancedfilters.DifferenceOfGaussian3D;
import net.haesleinhuepf.clij.advancedfilters.Extrema;
import net.haesleinhuepf.clij.advancedfilters.LocalExtremaBox;
import net.haesleinhuepf.clij.advancedfilters.TopHatBox;
import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij.piv.ParticleImageVelocimetryTimelapse;
import net.haesleinhuepf.clij.piv.visualisation.VisualiseVectorFieldOnTimelapsePlugin;

public class PIVTimelapseDemo {
    public static void main(String... args) {
        new ImageJ();
        CLIJ clij = CLIJ.getInstance();

        ImagePlus imp = IJ.openImage("C:\\structure\\data\\piv\\julia\\z16_t30-50.tif");
        IJ.run(imp, "32-bit", "");

        ParticleImageVelocimetryTimelapse pivt = new ParticleImageVelocimetryTimelapse();
        pivt.setClij(clij);

        ClearCLBuffer input = clij.push(imp);
        ClearCLBuffer deltaX = clij.create(new long[]{input.getWidth(), input.getHeight(), input.getDepth() - 1}, input.getNativeType());
        ClearCLBuffer deltaY = clij.create(deltaX);
        int maxDelta = 5;

        pivt.setArgs(new Object[]{input, deltaX, deltaY, maxDelta});
        pivt.executeCL();

        clij.show(input, "input");
        clij.show(deltaX, "deltaX");
        clij.show(deltaY, "deltaY");

        ClearCLBuffer blurredDeltaX = clij.create(input);
        ClearCLBuffer blurredDeltaY = clij.create(input);

        //clij.op().blur(deltaX, blurredDeltaX, (float)maxDelta, (float)maxDelta, 0f);
        //clij.op().blur(deltaY, blurredDeltaY, (float)maxDelta, (float)maxDelta, 0f);
        DifferenceOfGaussian3D.differenceOfGaussian(clij, deltaX, blurredDeltaX, (float)maxDelta, (float)maxDelta, 0f, (float)maxDelta * 2, (float)maxDelta * 2, 0f);
        DifferenceOfGaussian3D.differenceOfGaussian(clij, deltaY, blurredDeltaY, (float)maxDelta, (float)maxDelta, 0f, (float)maxDelta * 2, (float)maxDelta * 2, 0f);

        //LocalExtremaBox.localExtrema(clij, deltaX, blurredDeltaX, 1, 1, 0);
        //LocalExtremaBox.localExtrema(clij, deltaY, blurredDeltaY, 1, 1, 0);

        clij.show(blurredDeltaX, "blurredDeltaX");

        ImagePlus deltaXImp = clij.pull(blurredDeltaX);
        ImagePlus deltaYImp = clij.pull(blurredDeltaY);

        ImagePlus inputImp = clij.pull(input);
        inputImp.setDisplayRange(imp.getDisplayRangeMin(), imp.getDisplayRangeMax());
        inputImp.show();

        VisualiseVectorFieldOnTimelapsePlugin vvfotp = new VisualiseVectorFieldOnTimelapsePlugin();
        vvfotp.setInputImage(inputImp);
        vvfotp.setShowResult(false);
        vvfotp.setSilent(true);
        vvfotp.setLineWidth(1);
        vvfotp.setMinimumLength(0);
        vvfotp.setMaximumLength(maxDelta);
        vvfotp.setVectorXImage(deltaXImp);
        vvfotp.setVectorYImage(deltaYImp);
        vvfotp.setStepSize(5);
        vvfotp.run();
        vvfotp.getOutputImage().show();

        blurredDeltaX.close();
        input.close();
        deltaX.close();
        deltaY.close();



    }
}
