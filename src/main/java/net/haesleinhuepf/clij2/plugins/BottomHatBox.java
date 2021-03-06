package net.haesleinhuepf.clij2.plugins;


import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij.macro.CLIJMacroPlugin;
import net.haesleinhuepf.clij.macro.CLIJOpenCLProcessor;
import net.haesleinhuepf.clij.macro.documentation.OffersDocumentation;
import net.haesleinhuepf.clij2.AbstractCLIJ2Plugin;
import net.haesleinhuepf.clij2.CLIJ2;
import net.haesleinhuepf.clij2.utilities.HasClassifiedInputOutput;
import net.haesleinhuepf.clij2.utilities.IsCategorized;
import org.scijava.plugin.Plugin;

@Plugin(type = CLIJMacroPlugin.class, name = "CLIJ2_bottomHatBox")
public class BottomHatBox extends AbstractCLIJ2Plugin implements CLIJMacroPlugin, CLIJOpenCLProcessor, OffersDocumentation, IsCategorized, HasClassifiedInputOutput {
    @Override
    public String getInputType() {
        return "Image";
    }

    @Override
    public String getOutputType() {
        return "Image";
    }

    @Override
    public String getParameterHelpText() {
        return "Image input, ByRef Image destination, Number radiusX, Number radiusY, Number radiusZ";
    }

    @Override
    public boolean executeCL() {

        Object[] args = openCLBufferArgs();
        boolean result = getCLIJ2().bottomHatBox((ClearCLBuffer) (args[0]), (ClearCLBuffer) (args[1]), asInteger(args[2]), asInteger(args[3]), asInteger(args[4]));
        releaseBuffers(args);
        return result;
    }

    public static boolean bottomHatBox(CLIJ2 clij2, ClearCLBuffer input, ClearCLBuffer output, Integer radiusX, Integer radiusY, Integer radiusZ) {

        ClearCLBuffer temp1 = clij2.create(input);
        ClearCLBuffer temp2 = clij2.create(input);

        if (input.getDimension() == 3) {
            clij2.maximum3DBox(input, temp1, radiusX, radiusX, radiusZ);
            clij2.minimum3DBox(temp1, temp2, radiusX, radiusY, radiusZ);
        } else {
            clij2.maximum2DBox(input, temp1, radiusX, radiusX);
            clij2.minimum2DBox(temp1, temp2, radiusX, radiusY);
        }
        clij2.subtractImages(temp2, input, output);

        clij2.release(temp1);
        clij2.release(temp2);
        return true;
    }

    @Override
    public String getDescription() {
        return "Apply a bottom-hat filter for background subtraction to the input image.\n\n" +
                "Parameters\n" +
                "----------\n" +
                "input : Image\n" +
                "    The input image where the background is subtracted from.\n" +
                "destination : Image\n" +
                "    The output image where results are written into.\n" +
                "radius_x : Image\n" +
                "    Radius of the background determination region in X.\n" +
                "radius_y : Image\n" +
                "    Radius of the background determination region in Y.\n" +
                "radius_z : Image\n" +
                "    Radius of the background determination region in Z.\n";
    }

    @Override
    public String getAvailableForDimensions() {
        return "2D, 3D";
    }

    @Override
    public String getCategories() {
        return "Background, Filter";
    }
}
