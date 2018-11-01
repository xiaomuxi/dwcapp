package com.weddingcar.driver.function.main.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.network.library.bean.user.response.SignUpInfoEntity;
import com.network.library.utils.GlideUtils;
import com.weddingcar.driver.R;
import com.weddingcar.driver.common.config.Config;
import com.weddingcar.driver.common.utils.UIUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class SignUpAdapter extends BaseAdapter {
    private Context mContext;
    private List<SignUpInfoEntity.OrderOffer> mSignUpList;

    public SignUpAdapter(Context context, List<SignUpInfoEntity.OrderOffer> signUpList) {
        this.mContext = context;
        this.mSignUpList = signUpList;
    }

    @Override
    public int getCount() {
        return mSignUpList.size();
    }

    @Override
    public SignUpInfoEntity.OrderOffer getItem(int position) {
        return mSignUpList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = UIUtils.inflate(mContext, R.layout.item_sign_up_view);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        SignUpInfoEntity.OrderOffer signUpInfo = mSignUpList.get(i);
        String name = signUpInfo.getName();
        String signUpName = name + "师傅";
        String evaluate = signUpInfo.getEvaluate();
        String carPlate = signUpInfo.getCarPlate();
        String orderCount = signUpInfo.getOrderAmount() + "单";
        String carColor = signUpInfo.getCarColor();
        String signPrice = "￥" + signUpInfo.getAmount();
        String signUpIcon = Config.getAppHtmlUrl() + "/LJTP/CATP/" + signUpInfo.getAvator();

        holder.signUpCarNick.setText(signUpName);
        holder.signUpCarStar.setText(evaluate);
        holder.signUpCarColor.setText(carColor);
        holder.signUpCarNumber.setText(carPlate);
        holder.signUpCarCount.setText(orderCount);
        holder.signUpPrice.setText(signPrice);
        GlideUtils.loadShow(mContext, signUpIcon, holder.signUpIcon);
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.sign_up_icon)
        CircleImageView signUpIcon;
        @BindView(R.id.sign_up_car_nick)
        TextView signUpCarNick;
        @BindView(R.id.sign_up_car_star)
        TextView signUpCarStar;
        @BindView(R.id.sign_up_car_color)
        TextView signUpCarColor;
        @BindView(R.id.sign_up_car_number)
        TextView signUpCarNumber;
        @BindView(R.id.sign_up_car_count)
        TextView signUpCarCount;
        @BindView(R.id.sign_up_price)
        TextView signUpPrice;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
