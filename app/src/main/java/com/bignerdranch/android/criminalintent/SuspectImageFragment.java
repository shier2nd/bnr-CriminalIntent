package com.bignerdranch.android.criminalintent;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import java.io.File;

/**
 * Created by Woodinner on 16/1/26.
 */
public class SuspectImageFragment extends DialogFragment {

    private static final String ARG_SUSPECT_IMAGE = "suspect_image";

    public static SuspectImageFragment newInstance(File photoFile) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_SUSPECT_IMAGE, photoFile);

        SuspectImageFragment fragment = new SuspectImageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final File photoFile = (File) getArguments().getSerializable(ARG_SUSPECT_IMAGE);
        Bitmap image = PictureUtils.getScaledBitmap(photoFile.getPath(), getActivity());

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_suspect_image, null);

        ImageView imageView = (ImageView) v.findViewById(R.id.dialog_suspect_image_image_view);
        imageView.setImageBitmap(image);

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.suspect_image_title)
                .setPositiveButton(R.string.delete_image, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, null);
                    }
                })
                .create();
    }
}
