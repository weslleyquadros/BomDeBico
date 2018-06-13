package com.example.weslleyq.bomdebico;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;



public class AnuncioFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    RecyclerView dataSource;
    ArrayList<ItemAnuncios> lista;

    public AnuncioFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static AnuncioFragment newInstance(String param1, String param2) {
        AnuncioFragment fragment = new AnuncioFragment();
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
    private DatabaseReference mDatabase;
    ItemAnuncios inserirAnuncios;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_anuncio, container, false);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        lista=new ArrayList<>();
        dataSource= (RecyclerView) view.findViewById(R.id.lista);
        dataSource.setLayoutManager(new LinearLayoutManager(getActivity()));



        mDatabase.child("Anuncio").addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //vai salvar a data de cadastro , para diferenciar cada cadaastro
                        for(DataSnapshot objSnapShot: dataSnapshot.getChildren()){
                            ItemAnuncios anuncio = objSnapShot.getValue(ItemAnuncios.class);
                            //ListaAnuncios(anuncio);
                            lista.add(anuncio);

                            Log.i("TAG","");
                        }

                        AdapterAnuncio adapter=new AdapterAnuncio(lista);
                        dataSource.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.i("LOG", String.valueOf(databaseError));
//handle databaseError
                    }



                });

        //ListaAnuncios(inserirAnuncios);


        return view;
    }

    private void ListaAnuncios(ItemAnuncios Anuncio) {
        lista.add(new ItemAnuncios(Anuncio.getTitulo(),Anuncio.getDescricao(),Anuncio.getTelefone(), Anuncio.getDatacadastro()));
        //lista.add(new ItemAnuncios("Formatação","Formatação é comigo mesmo, sou Daniel Ferreira","999-99999","hoje"));
       /* lista.add(new ItemAnuncios("Moveis Planejados","Sou Matheus José,contato (63) 98415-6688, Facebook: https://www.facebook.com/oficinacriar/",4,R.drawable.oficinacriar));
        lista.add(new ItemAnuncios("Pintura em geral","Faço pitura em geral, favor entrar em contato . (63) 98214-1937 (Claudio).",2,R.drawable.pintura)); */
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
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
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
}
