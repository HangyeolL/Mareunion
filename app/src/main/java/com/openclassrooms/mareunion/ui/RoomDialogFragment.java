package com.openclassrooms.mareunion.ui;

import android.os.Bundle;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.openclassrooms.mareunion.databinding.FragmentRoomDialogBinding;
import com.openclassrooms.mareunion.di.DI;
import com.openclassrooms.mareunion.event.RoomRecyclerViewItemClickEvent;
import com.openclassrooms.mareunion.interfaces.RoomRecyclerViewItemClickListener;
import com.openclassrooms.mareunion.model.Room;
import com.openclassrooms.mareunion.service.MeetingApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public class RoomDialogFragment extends DialogFragment {

    FragmentRoomDialogBinding mFragmentRoomDialogBinding;

    private MeetingApiService mApiService;

    private RoomRecyclerViewItemClickListener mRoomRecyclerViewItemClickListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getMeetingApiService();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentRoomDialogBinding = FragmentRoomDialogBinding.inflate(inflater, container, false);
        mFragmentRoomDialogBinding.recyclerViewRoomDialogFragment.setLayoutManager(new LinearLayoutManager(mFragmentRoomDialogBinding.getRoot().getContext()));

        return mFragmentRoomDialogBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();

        // Make DialogFragment's size to MATCH_PARENT
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);

        // Init RecyclerView List
        List<Room> mRoomList = mApiService.getRooms();
        mFragmentRoomDialogBinding.recyclerViewRoomDialogFragment.setAdapter(new RoomRecyclerViewAdapter(mRoomList));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mFragmentRoomDialogBinding = null;
    }

    /**
     * Eventbus
     */

    @Override
    public void onStart() {
        EventBus.getDefault().register(this);
        super.onStart();
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Subscribe
    public void RoomRecyclerViewItemClickEvent(RoomRecyclerViewItemClickEvent event) {
        mRoomRecyclerViewItemClickListener.selectedRoom(event.mRoom);
        dismiss();
    }

    public void filterRoom(RoomRecyclerViewItemClickListener roomRecyclerViewItemClickListener) {
        mRoomRecyclerViewItemClickListener = roomRecyclerViewItemClickListener;
    }

}
