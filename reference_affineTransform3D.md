## affineTransform3D
![Image](images/mini_clij1_logo.png)

Applies an affine transform to a 3D image. Individual transforms must be separated by spaces.

Supported transforms:
* center: translate the coordinate origin to the center of the image
* -center: translate the coordinate origin back to the initial origin
* rotate=[angle]: rotate in X/Y plane (around Z-axis) by the given angle in degrees
* rotateX=[angle]: rotate in Y/Z plane (around X-axis) by the given angle in degrees
* rotateY=[angle]: rotate in X/Z plane (around Y-axis) by the given angle in degrees
* rotateZ=[angle]: rotate in X/Y plane (around Z-axis) by the given angle in degrees
* scale=[factor]: isotropic scaling according to given zoom factor
* scaleX=[factor]: scaling along X-axis according to given zoom factor
* scaleY=[factor]: scaling along Y-axis according to given zoom factor
* scaleZ=[factor]: scaling along Z-axis according to given zoom factor
* shearXY=[factor]: shearing along X-axis in XY plane according to given factor
* shearXZ=[factor]: shearing along X-axis in XZ plane according to given factor
* shearYX=[factor]: shearing along Y-axis in XY plane according to given factor
* shearYZ=[factor]: shearing along Y-axis in YZ plane according to given factor
* shearZX=[factor]: shearing along Z-axis in XZ plane according to given factor
* shearZY=[factor]: shearing along Z-axis in YZ plane according to given factor
* translateX=[distance]: translate along X-axis by distance given in pixels
* translateY=[distance]: translate along X-axis by distance given in pixels
* translateZ=[distance]: translate along X-axis by distance given in pixels

Example transform:
transform = "center scale=2 rotate=45 -center";

### Usage in ImageJ macro
```
Ext.CLIJ_affineTransform3D(Image source, Image destination, String transform);
```


### Usage in Java
```
// init CLIJ and GPU
import net.haesleinhuepf.clijx.CLIJ;
import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
CLIJx clijx = CLIJx.getInstance();

// get input parameters
ClearCLBuffer arg1 = clijx.push(arg1ImagePlus);
ClearCLBuffer arg2 = clijx.push(arg2ImagePlus);
```

```
// Execute operation on GPU
clijx.affineTransform3D(clij, arg1, arg2, arg3);
```

```
//show result

// cleanup memory on GPU
arg1.close();
arg2.close();
```




### Example scripts
<a href="https://github.com/clij/clij-advanced-filters/blob/master/src/main/jython/"><img src="images/language_jython.png" height="20"/></a> [interactiveCylinderProjection.py](https://github.com/clij/clij-advanced-filters/blob/master/src/main/jython/interactiveCylinderProjection.py)  
<a href="https://github.com/clij/clij-advanced-filters/blob/master/src/main/jython/"><img src="images/language_jython.png" height="20"/></a> [interactiveSphereProjection.py](https://github.com/clij/clij-advanced-filters/blob/master/src/main/jython/interactiveSphereProjection.py)  
<a href="https://github.com/clij/clij-docs/blob/master/src/main/jython/"><img src="images/language_jython.png" height="20"/></a> [interactiveSpotDetection.py](https://github.com/clij/clij-docs/blob/master/src/main/jython/interactiveSpotDetection.py)  
<a href="https://github.com/clij/clij-docs/blob/master/src/main/jython/"><img src="images/language_jython.png" height="20"/></a> [spotDetectionpy.py](https://github.com/clij/clij-docs/blob/master/src/main/jython/spotDetectionpy.py)  
<a href="https://github.com/clij/clij-docs/blob/master/src/main/java/net/haesleinhuepf/clij/examples/"><img src="images/language_java.png" height="20"/></a> [AffineTransformDemo.java](https://github.com/clij/clij-docs/blob/master/src/main/java/net/haesleinhuepf/clij/examples/AffineTransformDemo.java)  


[Back to CLIJ documentation](https://clij.github.io/)

[Imprint](https://clij.github.io/imprint)