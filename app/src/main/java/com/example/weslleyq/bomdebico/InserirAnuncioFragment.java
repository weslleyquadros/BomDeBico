package com.example.weslleyq.bomdebico;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
public class InserirAnuncioFragment extends Fragment {
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
