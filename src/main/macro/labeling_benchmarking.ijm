// CLIJ example macro: labeling.ijm
//
// This macro shows how to apply an automatic 
// threshold method and connected components labeling
// to an image on the GPU
//
// Author: Robert Haase
// June 2019
// ---------------------------------------------


// Get test data
//run("Blobs (25K)");

//open("C:/structure/data/blobs.gif");
getDimensions(width, height, channels, slices, frames);
input = getTitle();

labelmap = "labelmap";

// Init GPU
run("CLIJ Macro Extensions", "cl_device=");
Ext.CLIJ_clear();

// push data to GPU
Ext.CLIJ_push(input);

// cleanup ImageJ
run("Close All");

// create a mask using a fixed threshold
Ext.CLIJ_automaticThreshold(input, mask, "Otsu");

time = getTime();
Ext.CLIJ_connectedComponentsLabeling(mask, labelmap);

// show result
Ext.CLIJ_pull(labelmap);
run("glasbey on dark");

Ext.CLIJ_pull(mask);
setThreshold(1, 1);
setOption("BlackBackground", true);
run("Convert to Mask");

time = getTime();
run("Analyze Particles...", "  show=[Count Masks]");
print("IJ took" + (getTime() - time) + " msec"); 

