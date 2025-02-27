package com.fongmi.android.tv.ui.presenter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.leanback.widget.Presenter;

import com.fongmi.android.tv.R;
import com.fongmi.android.tv.api.ApiConfig;
import com.fongmi.android.tv.bean.History;
import com.fongmi.android.tv.databinding.AdapterVodBinding;
import com.fongmi.android.tv.utils.ImgUtil;
import com.fongmi.android.tv.utils.ResUtil;

public class HistoryPresenter extends Presenter {

    private OnClickListener mListener;
    private int width, height;
    private boolean delete;

    public HistoryPresenter() {
        setLayoutSize();
    }

    public interface OnClickListener {

        void onItemClick(History item);

        void onItemDelete(History item);

        boolean onLongClick();
    }

    public void setOnClickListener(OnClickListener listener) {
        this.mListener = listener;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    private void setLayoutSize() {
        int space = ResUtil.dp2px(112);
        int base = ResUtil.getScreenWidthPx() - space;
        width = base / 5;
        height = (int) (width / 0.75f);
    }

    @Override
    public Presenter.ViewHolder onCreateViewHolder(ViewGroup parent) {
        ViewHolder holder = new ViewHolder(AdapterVodBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
        holder.binding.getRoot().getLayoutParams().width = width;
        holder.binding.getRoot().getLayoutParams().height = height;
        return holder;
    }

    @Override
    public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object object) {
        History item = (History) object;
        ViewHolder holder = (ViewHolder) viewHolder;
        holder.binding.name.setText(item.getVodName());
        holder.binding.site.setText(ApiConfig.getSiteName(item.getSiteKey()));
        holder.binding.remark.setText(ResUtil.getString(R.string.vod_last, item.getVodRemarks()));
        holder.binding.name.setVisibility(delete ? View.GONE : View.VISIBLE);
        holder.binding.site.setVisibility(delete ? View.GONE : View.VISIBLE);
        holder.binding.remark.setVisibility(delete ? View.GONE : View.VISIBLE);
        holder.binding.delete.setVisibility(!delete ? View.GONE : View.VISIBLE);
        ImgUtil.load(item.getVodPic(), holder.binding.image);
        setClickListener(holder.view, item);
    }

    private void setClickListener(View root, History item) {
        root.setOnLongClickListener(view -> mListener.onLongClick());
        root.setOnClickListener(view -> {
            if (isDelete()) mListener.onItemDelete(item);
            else mListener.onItemClick(item);
        });
    }

    @Override
    public void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {
    }

    public static class ViewHolder extends Presenter.ViewHolder {

        private final AdapterVodBinding binding;

        public ViewHolder(@NonNull AdapterVodBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}