/*
 * Copyright 2016 Keval Patel.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.lida.myapplication.camera;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.PixelFormat;
import android.support.annotation.RequiresPermission;
import android.support.v4.app.ActivityCompat;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.example.lida.myapplication.camera.config.CameraFacing;
import com.example.lida.myapplication.camera.config.HiddenCameraUtils;


/**
 * Created by Keval on 27-Oct-16.
 * This abstract class provides ability to handle background camera to the service in which it is
 * extended.
 *
 * @author {@link 'https://github.com/kevalpatel2106'}
 */

public abstract class HiddenCameraService extends Service implements CameraCallbacks {

    private CameraPreview mCameraPreview;

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        stopCamera();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopCamera();
    }


    @RequiresPermission(allOf = {Manifest.permission.CAMERA, Manifest.permission.SYSTEM_ALERT_WINDOW})
    public void startCamera(CameraConfig cameraConfig) {

        if (!HiddenCameraUtils.canOverDrawOtherApps(this)) {    //Check if the draw over other app permission is available.

            onCameraError(CameraError.ERROR_DOES_NOT_HAVE_OVERDRAW_PERMISSION);
        } else if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) { //check if the camera permission is available

            //Throw error if the camera permission not available
            onCameraError(CameraError.ERROR_CAMERA_PERMISSION_NOT_AVAILABLE);
        } else if (cameraConfig.getFacing() == CameraFacing.REAR_FACING_CAMERA
                && !HiddenCameraUtils.isFrontCameraAvailable(this)) {   //Check if for the front camera

            onCameraError(CameraError.ERROR_DOES_NOT_HAVE_FRONT_CAMERA);
        } else {

            //Add the camera preview surface to the root of the activity view.
            if (mCameraPreview == null) mCameraPreview = addPreView();
            mCameraPreview.startCameraInternal(cameraConfig);
        }
    }


    public void takePicture() {
        if (mCameraPreview != null && mCameraPreview.isSafeToTakePictureInternal()) {
            mCameraPreview.takePictureInternal();
        } else {
            throw new RuntimeException("Background camera not initialized. Call startCamera() to initialize the camera.");
        }
    }

    /**
     * Stop and release the camera forcefully.
     */
    public void stopCamera() {
        if (mCameraPreview != null) mCameraPreview.stopPreviewAndFreeCamera();
    }

    /**
     * Add camera preview to the root of the activity layout.
     *
     * @return {@link CameraPreview} that was added to the view.
     */
    private CameraPreview addPreView() {
        //create fake camera view
        CameraPreview cameraSourceCameraPreview = new CameraPreview(this, this);
        cameraSourceCameraPreview.setLayoutParams(new ViewGroup
                .LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(1, 1,
                WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,
                WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                PixelFormat.TRANSLUCENT);

        wm.addView(cameraSourceCameraPreview, params);
        return cameraSourceCameraPreview;
    }
}