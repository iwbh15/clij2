package net.haesleinhuepf.clij2.plugins;

import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij.clearcl.interfaces.ClearCLImageInterface;
import net.haesleinhuepf.clij.macro.CLIJMacroPlugin;
import net.haesleinhuepf.clij.macro.CLIJOpenCLProcessor;
import net.haesleinhuepf.clij.macro.documentation.OffersDocumentation;
import net.haesleinhuepf.clij2.AbstractCLIJ2Plugin;
import net.haesleinhuepf.clij2.CLIJ2;
import net.haesleinhuepf.clij2.utilities.HasClassifiedInputOutput;
import net.haesleinhuepf.clij2.utilities.IsCategorized;
import org.scijava.plugin.Plugin;

import java.util.HashMap;

import static net.haesleinhuepf.clij.utilities.CLIJUtilities.assertDifferent;

/**
 * Author: @haesleinhuepf
 * 12 2018
 */

@Plugin(type = CLIJMacroPlugin.class, name = "CLIJ2_binaryNot")
public class BinaryNot extends AbstractCLIJ2Plugin implements CLIJMacroPlugin, CLIJOpenCLProcessor, OffersDocumentation, IsCategorized, HasClassifiedInputOutput {
    @Override
    public String getInputType() {
        return "Binary Image";
    }

    @Override
    public String getOutputType() {
        return "Binary Image";
    }

    @Override
    public boolean executeCL() {
        getCLIJ2().binaryNot((ClearCLBuffer)( args[0]), (ClearCLBuffer)(args[1]));
        return true;
    }


    public static boolean binaryNot(CLIJ2 clij2, ClearCLImageInterface src1, ClearCLImageInterface dst) {
        assertDifferent(src1, dst);

        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("src1", src1);
        parameters.put("dst", dst);

        clij2.execute(BinaryNot.class, "binary_not_" + src1.getDimension() + "d_x.cl", "binary_not_" + src1.getDimension() + "d", dst.getDimensions(), dst.getDimensions(), parameters);
        return true;
    }

    @Override
    public String getParameterHelpText() {
        return "Image source, ByRef Image destination";
    }

    @Override
    public String getDescription() {
        return "Computes a binary image (containing pixel values 0 and 1) from an image X by negating its pixel values\n" +
                "x using the binary NOT operator !\n\n" +
                "All pixel values except 0 in the input image are interpreted as 1.\n\n" +
                "<pre>f(x) = !x</pre>\n\n" +
                "Parameters\n" +
                "----------\n" +
                "source : Image\n" +
                "    The binary input image to be inverted.\n" +
                "destination : Image\n" +
                "    The output image where results are written into.\n";
    }

    @Override
    public String getAvailableForDimensions() {
        return "2D, 3D";
    }

    @Override
    public String getCategories() {
        return "Binary, Filter, Math";
    }
}
