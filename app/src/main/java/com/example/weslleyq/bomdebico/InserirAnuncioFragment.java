package com.example.weslleyq.bomdebico;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link InserirAnuncioFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link InserirAnuncioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InserirAnuncioFragment extends Fragment  {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    EditText CampoTitulo;
    EditText CampoDescricao;
    EditText Campofone;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Uri imageUri;
    ImageView campoFoto;
    private String caminhoFoto;
    private GoogleApiClient mGoogleApiClient;
    private DatabaseReference mDatabase;

    private OnFragmentInteractionListener mListener;

    public InserirAnuncioFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InserirAnuncioFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InserirAnuncioFragment newInstance(String param1, String param2) {
        InserirAnuncioFragment fragment = new InserirAnuncioFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View viewFragment= inflater.inflate(R.layout.fragment_inserir_anuncio, container, false);


        CampoTitulo = (EditText) viewFragment.findViewById(R.id.CampoTitulo);
        CampoDescricao = (EditText) viewFragment.findViewById(R.id.CampoDescricao);
        Campofone = (EditText) viewFragment.findViewById(R.id.Campofone);
        campoFoto = (ImageView)viewFragment.findViewById(R.id.imgDoAnuncio);


        campoFoto.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             takePhoto();
                                         }
        });

        //callConnection();

        Button btnCadastrar = (Button) viewFragment.findViewById(R.id.buttonCadastrar);
        btnCadastrar.setOnClickListener(new View.OnClickListener() {

         @Override
         public void onClick(View v) {


             SimpleDateFormat formataData = new SimpleDateFormat("dd-MM-yyyy");


             ItemAnuncios inserir = new ItemAnuncios(CampoTitulo.getText().toString(), CampoDescricao.getText().toString(),
                     Campofone.getText().toString(),     formataData.format(new Date()));
             mDatabase = FirebaseDatabase.getInstance().getReference();

             mDatabase.child("Anuncio").child(String.valueOf(new Date())).setValue(inserir);

             Toast.makeText(getContext(), " Cadastrado com Sucesso", Toast.LENGTH_SHORT).show();


         }
        });
        return viewFragment;
    }
    //METODO QUE UTILIZA O SYCRONIZED DO JAVA, PARA FAZER UMA CONEXÃO
   /* private synchronized void callConnection() {
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity()).addOnConnectionFailedListener(this).addConnectionCallbacks(this).addApi(LocationServices.API).build();
        mGoogleApiClient.connect();
    } */

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof SobreFragment.OnFragmentInteractionListener) {
            mListener = (InserirAnuncioFragment.OnFragmentInteractionListener) context;
        } else {
            Toast.makeText(context,"Insirar um Bico" ,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void takePhoto() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setTitle("Choose Image Source");
        builder.setItems(new CharSequence[]{"Gallery", "Camera"}, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:

                        Intent intent = new Intent(
                                Intent.ACTION_GET_CONTENT);
                        intent.setType("image/*");

                        Intent chooser = Intent.createChooser(intent, "Choose a Picture");
                        startActivityForResult(chooser, 0);

                        break;

                    case 1:

                        Intent intentTirarFoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        caminhoFoto = getContext().getExternalFilesDir(null) + "/" + System.currentTimeMillis() + ".jpg";
                        File foto = new File(caminhoFoto);
                        imageUri = FileProvider.getUriForFile(getContext(), BuildConfig.APPLICATION_ID + ".provider", foto);
                        intentTirarFoto.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                        startActivityForResult(intentTirarFoto,1);

                        break;

                }
            }

        });

        builder.show();
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {

            switch (requestCode) {
                case 0:


                    Uri selectedImageUri = data.getData();

                    try {
                        Uri selectedImage = selectedImageUri;
                        //getContentResolver().notifyChange(selectedImage, null);
                        ContentResolver cr = getActivity().getContentResolver();

                        Bitmap bitmap;
                        bitmap = MediaStore.Images.Media
                                .getBitmap(cr, selectedImage);
                        campoFoto.setImageBitmap(bitmap);

                    } catch (Exception e) {
                        Toast.makeText(getActivity(), "Failed to load", Toast.LENGTH_SHORT)
                                .show();
                        Log.e("Camera", e.toString());
                    }


                    break;

                case 1:
                    super.onActivityResult(requestCode, resultCode, data);
                    if (resultCode == Activity.RESULT_OK) {
                        Uri selectedImage = imageUri;
                        getActivity().getContentResolver().notifyChange(selectedImage, null);
                        ContentResolver cr = getActivity().getContentResolver();
                        Bitmap bitmap;
                        try {
                            bitmap = MediaStore.Images.Media
                                    .getBitmap(cr, selectedImage);

                            campoFoto.setImageBitmap(bitmap);
                            Toast.makeText(getActivity(), selectedImage.toString(),
                                    Toast.LENGTH_LONG).show();
                        } catch (Exception e) {
                            Toast.makeText(getActivity(), "Failed to load", Toast.LENGTH_SHORT)
                                    .show();
                            Log.e("Camera", e.toString());
                        }
                    }
                    break;
            }
        }
    }


}
