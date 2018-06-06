package com.rongke.baselibrary.network;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.rongke.baselibrary.util.CommonUtil;
import com.rongke.baselibrary.view.LoadingDialog;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jh352160 on 2017/9/8.
 */

public abstract class BaseCallBack<T> implements Callback<T> {
    private LoadingDialog mProgressDialog;//进度窗体
    private Context context;

    public BaseCallBack(Context context){
        this.context = context;
    }

    /**
     * 传入Context则显示进度条
     */
    public BaseCallBack(Context context,FragmentManager fragmentManager){
        this.context = context;
        showInfoProgressDialog(fragmentManager,null);
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        onFinish();
        if (response.code()==200){
            onSuccess(call,response);
        }else{
            onError(call,response);
        }
    }

    /**
     * response中的code不为200的情况
     */
    public void onError(Call<T> call, Response<T> response) {
        CommonUtil.showToast(context, response.message());
        Log.e("NetWorkError",response.message());
    }

    /**
     * response中的code为200
     */
    public abstract void onSuccess(Call<T> call, Response<T> response);

    /**
     * 网络请求出现异常
     */
    @Override
    public void onFailure(Call call, Throwable t) {
        onFinish();
        CommonUtil.showToast(context, t.getMessage());
        Log.e("NetWorkFail",t.toString());
    }

    /**
     * 隐藏等待条
     */
    private void hideInfoProgressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
            mProgressDialog=null;
        }
    }

    /**
     * 显示等待条
     */
    private  void showInfoProgressDialog(FragmentManager context, final String str) {
        if (mProgressDialog == null) {
            mProgressDialog = new LoadingDialog();
        }

        if (!mProgressDialog.isVisible()) {
            mProgressDialog.show(context,"loading");
        }
    }

    /**
     * 当请求完成时调用（无论成功或失败）
     */
    public void onFinish(){
        //如果没有加入进度条操作可以不调用super
        hideInfoProgressDialog();
    }
}
