package com.rongke.baselibrary.base.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rongke.baselibrary.util.AutoUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jh352160 on 2018/2/5.
 */

public abstract class BaseRecyclerAdapter<M> extends RecyclerView.Adapter{
    private final int TYPE_HEADER = -1;
    private final int TYPE_FOOTER = -2;

    protected Context mContext;
    protected List<M> datas;
    protected OnItemClickListener<M> onItemClickListener;

    private View footerView;
    private View headerView;

    /**
     * 空数据构造方法
     */
    public BaseRecyclerAdapter(Context mContext) {
        this(mContext,new ArrayList<M>());
    }

    public BaseRecyclerAdapter(Context mContext,List<M> datas) {
        this.mContext=mContext;
        this.datas = datas;
    }

    protected abstract @LayoutRes int setLayoutResId(int viewType);

    @Override
    public int getItemViewType(int position) {
        if (headerView!=null&&(position==0)){
            return TYPE_HEADER;
        }
        if (footerView!=null && (position == getItemCount()-1)){
            return TYPE_FOOTER;
        }
        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_FOOTER){
            return new HeaderAndFooterViewHolder(footerView);
        }
        if (viewType == TYPE_HEADER){
            return new HeaderAndFooterViewHolder(headerView);
        }
        View view = LayoutInflater.from(mContext).inflate(setLayoutResId(viewType), parent, false);
        AutoUtils.auto(view);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        //头布局与尾布局不走接下来的方法
        if (footerView!=null && (position == getItemCount()-1)){
            return;
        }
        if (headerView!=null){
            if (position==0) {
                return;
            }else{
                //若存在头布局，position应该除去头布局，应为实际position-1
                position--;
            }
        }

        onBindViewHolder(datas.get(position), (ViewHolder)holder, position);

        //item点击事件
        if (onItemClickListener!=null) {
            final int finalPosition = position;
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(datas.get(finalPosition), finalPosition);
                }
            });
        }
    }

    public abstract void onBindViewHolder(M itemData, ViewHolder holder, int position);

    @Override
    public int getItemCount() {
        int itemCount = datas.size();
        if (footerView!=null) itemCount++;
        if (headerView!=null) itemCount++;

        return itemCount;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    /**
     * adapter中header与footer的viewholder
     */
    class HeaderAndFooterViewHolder extends RecyclerView.ViewHolder {
        HeaderAndFooterViewHolder(View view) {
            super(view);
        }
    }

    /**
     * 添加尾布局
     */
    public void addFooter(View view){
        footerView=view;
    }

    /**
     * 获取尾布局
     */
    public View getFooter(){
        return footerView;
    }

    /**
     * 添加头布局
     */
    public void addHeader(View view){
        headerView=view;
    }

    /**
     * 获取头布局
     */
    public View getHeader(){
        return headerView;
    }

    public void setOnItemClickListener(OnItemClickListener<M> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener<M>{
        void onItemClick(M item, int position);
    }

    public void setData(List<M> datas){
        this.datas = datas;
        notifyDataSetChanged();
    }

    public void addAll(List<M> data) {
        datas.addAll(data);
        notifyDataSetChanged();
    }

    public void add(M object) {
        datas.add(object);
        notifyDataSetChanged();
    }

    public void add(int position,M object) {
        datas.add(position,object);
        notifyDataSetChanged();
    }

    public void clear() {
        datas.clear();
        notifyDataSetChanged();
    }

    /**
     * 由于getItemCount方法会被header或footer影响，如果需要获取数据的数量，推荐使用该方法
     */
    public int getSize(){
        return datas.size();
    }
}
