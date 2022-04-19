package com.example.deliveryapp.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.deliveryapp.R;

public class DilogFragment extends DialogFragment {

    private static final String ARG_TiTle="title";
    private static final String ARG_MESSAGE="message";
    private static final String ARG_ICON="icon";

    private  String Title;
    private  String message;
    private  int Icon;

    private OnPostiveButton onPostiveButton;
    private OnNegativeButton onNegativeButton;

    public DilogFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof OnPostiveButton)
        {
            onPostiveButton=(OnPostiveButton)context;
        }
        else {
            throw new RuntimeException("Plase make shor that ur implemnt onPostiveButton");
        }

        if(context instanceof OnNegativeButton)
        {
            onNegativeButton=(OnNegativeButton)context;
        }
        else {
            throw new RuntimeException("Plase make shor that ur implemnt onPostiveButton");
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();

        onNegativeButton=null;
        onPostiveButton=null;
    }

    public static DilogFragment newInstance(String Title, String message , int icon) {

        DilogFragment fragment = new DilogFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_TiTle,Title);
        bundle.putString(ARG_MESSAGE,message);
        bundle.putInt(ARG_ICON,icon);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle!=null)
        {
            Title = bundle.getString(ARG_TiTle);
            message = bundle.getString(ARG_MESSAGE);
            Icon = bundle.getInt(ARG_ICON);
        }

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(Title);
        builder.setMessage(message);
        builder.setIcon(Icon);

        builder.setPositiveButton(getString(R.string.login), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //   Toast.makeText(getActivity(),"Aboud Really love you",Toast.LENGTH_LONG).show();
                onPostiveButton.onpostiveClicked();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //       Toast.makeText(getActivity(),"Aboud Really like you",Toast.LENGTH_LONG).show();
                onNegativeButton.OnNegativeButtonClicked();
            }
        });

        return builder.create();

    }

    public  interface OnPostiveButton
    {
        void onpostiveClicked();
    }
    public  interface OnNegativeButton
    {
        void OnNegativeButtonClicked();
    }


}