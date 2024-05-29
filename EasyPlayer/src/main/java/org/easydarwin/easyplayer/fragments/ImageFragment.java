package org.easydarwin.easyplayer.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.target.ImageViewTarget;

import org.easydarwin.easyplayer.R;
import org.easydarwin.easyplayer.databinding.FragmentImageBinding;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ImageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ImageFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private Uri mUri;
    private FragmentImageBinding mBinding;
    private PhotoViewAttacher mAttacher;

    public ImageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param uri Parameter 1.
     * @return A new instance of fragment ImageFragment.
     */
    public static ImageFragment newInstance(Uri uri) {
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, uri);

        ImageFragment fragment = new ImageFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mUri = getArguments().getParcelable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_image, container, false);
        mBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Glide.with(this.getContext()).load(mUri).into(new ImageViewTarget<GlideDrawable>(mBinding.galleryImageView) {
            @Override
            protected void setResource(GlideDrawable resource) {
                mBinding.galleryImageView.setImageDrawable(resource);

                mAttacher = new PhotoViewAttacher(mBinding.galleryImageView);
//                mAttacher.setZoomable(false);
                mAttacher.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
                    @Override
                    public void onPhotoTap(View view, float v, float v1) {
                        getFragmentManager().popBackStack();
                    }

                    @Override
                    public void onOutsidePhotoTap() {
                        getFragmentManager().popBackStack();
                    }
                });

                mAttacher.setOnViewTapListener(new PhotoViewAttacher.OnViewTapListener() {
                    @Override
                    public void onViewTap(View view, float v, float v1) {
                        getFragmentManager().popBackStack();
                    }
                });
            }
        });
    }

    @Override
    public void onDestroyView() {
        if (mAttacher != null) {
            mAttacher.cleanup();
            mAttacher = null;
        }

        super.onDestroyView();
    }
}