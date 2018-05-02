package com.example.weslleyq.bomdebico;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AnuncioFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AnuncioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
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

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AnuncioFragment.
     */
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_anuncio, container, false);

        lista=new ArrayList<>();
        dataSource= (RecyclerView) view.findViewById(R.id.lista);
        dataSource.setLayoutManager(new LinearLayoutManager(getActivity()));

        ListaAnuncios();

        AdapterAnuncio adapter=new AdapterAnuncio(lista);
        dataSource.setAdapter(adapter);

        return view;
    }

    private void ListaAnuncios() {
        lista.add(new ItemAnuncios("Encanador","Faço qualquer tipo de encanação.",3,R.drawable.encanador));
        lista.add(new ItemAnuncios("Formatação","Formatação é comigo mesmo, sou Daniel Ferreira",4,R.drawable.formatacao));
        lista.add(new ItemAnuncios("Moveis Planejados","Sou Matheus José,contato (63) 98415-6688, Facebook: https://www.facebook.com/oficinacriar/",4,R.drawable.oficinacriar));
        lista.add(new ItemAnuncios("Pintura em geral","Faço pitura em geral, favor entrar em contato . (63) 98214-1937 (Claudio).",2,R.drawable.pintura));
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
