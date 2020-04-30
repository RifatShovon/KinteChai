package com.example.kintechai;


import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyAccountFragment extends Fragment {

    public MyAccountFragment() {
        // Required empty public constructor
    }

    private FloatingActionButton settingsBtn;
    private Button viewAllAddressBtn, signoutBtn;
    public static final int MANAGE_ADDRESS = 1;
    private CircleImageView profilView, currentOrderImage;
    private TextView name, email, tvCurrentOrderStatus;
    private LinearLayout layoutContainer, recentOrdersContainer;
    private Dialog loadingDialog;
    private ImageView orderIndicator, packedIndicator, shippedIndicator, deliveredIndicator;
    private ProgressBar O_P_progress, P_S_progress, S_D_progress;
    private TextView yourRecentOrdersTitle;
    private TextView addressName, address, pincode;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_account, container, false);

        ////////////////////////////// loading dialog
        loadingDialog = new Dialog(getContext());
        loadingDialog.setContentView(R.layout.loading_progress_dialog);
        loadingDialog.setCancelable(false);
        loadingDialog.getWindow().setBackgroundDrawable(getContext().getDrawable(R.drawable.slider_background));
        loadingDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        loadingDialog.show();
        ////////////////////////////// loading dialog //////////////////////////////////////

        layoutContainer = view.findViewById(R.id.layout_container);
        profilView = view.findViewById(R.id.profile_image);
        name = view.findViewById(R.id.user_name);
        email = view.findViewById(R.id.user_email);
        currentOrderImage = view.findViewById(R.id.current_order_image);
        tvCurrentOrderStatus = view.findViewById(R.id.tv_current_order_status);

        orderIndicator = view.findViewById(R.id.ordered_indicator);
        packedIndicator = view.findViewById(R.id.packed_indicator);
        shippedIndicator = view.findViewById(R.id.shipped_indicator);
        deliveredIndicator = view.findViewById(R.id.delivered_indicator);

        O_P_progress = view.findViewById(R.id.order_packed_progress);
        P_S_progress = view.findViewById(R.id.packed_shipped_progress);
        S_D_progress = view.findViewById(R.id.shipped_delivered_progress);

        yourRecentOrdersTitle = view.findViewById(R.id.your_recent_orders_title);
        recentOrdersContainer = view.findViewById(R.id.recent_orders_container);

        addressName = view.findViewById(R.id.address_fullname);
        address = view.findViewById(R.id.address);
        pincode = view.findViewById(R.id.address_pincode);

        signoutBtn = view.findViewById(R.id.sign_out_btn);

        settingsBtn = view.findViewById(R.id.settings_btn);


        layoutContainer.getChildAt(1).setVisibility(View.GONE);

        loadingDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {

                for (MyOrderItemModel orderItemModel : DBqueries.myOrderItemModelList) {
                    if (!orderItemModel.isCancellationRequested()) {
                        if (!orderItemModel.getOrderStatus().equals("Delivered") && !orderItemModel.getOrderStatus().equals("Cancelled")) {
                            layoutContainer.getChildAt(1).setVisibility(View.VISIBLE);
                            Glide.with(getContext()).load(orderItemModel.getProductImage()).apply(new RequestOptions().placeholder(R.mipmap.icon_placeholder)).into(currentOrderImage);
                            tvCurrentOrderStatus.setText(orderItemModel.getOrderStatus());

                            switch (orderItemModel.getOrderStatus()) {
                                case "Ordered":
                                    //orderIndicator.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.successGreen)));
                                    orderIndicator.setImageTintList(ColorStateList.valueOf(Color.parseColor("#00ff00")));
                                    break;
                                case "Packed":
                                    orderIndicator.setImageTintList(ColorStateList.valueOf(Color.parseColor("#00ff00")));
                                    packedIndicator.setImageTintList(ColorStateList.valueOf(Color.parseColor("#00ff00")));
                                    O_P_progress.setVisibility(View.VISIBLE);
                                    O_P_progress.setProgressBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#00ff00")));
                                    break;
                                case "Shipped":
                                    orderIndicator.setImageTintList(ColorStateList.valueOf(Color.parseColor("#00ff00")));
                                    packedIndicator.setImageTintList(ColorStateList.valueOf(Color.parseColor("#00ff00")));
                                    shippedIndicator.setImageTintList(ColorStateList.valueOf(Color.parseColor("#00ff00")));
                                    O_P_progress.setVisibility(View.VISIBLE);
                                    O_P_progress.setProgressBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#00ff00")));
                                    P_S_progress.setVisibility(View.VISIBLE);
                                    P_S_progress.setProgressBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#00ff00")));
                                    break;
                                case "Out for Delivery":
                                    orderIndicator.setImageTintList(ColorStateList.valueOf(Color.parseColor("#00ff00")));
                                    packedIndicator.setImageTintList(ColorStateList.valueOf(Color.parseColor("#00ff00")));
                                    shippedIndicator.setImageTintList(ColorStateList.valueOf(Color.parseColor("#00ff00")));
                                    deliveredIndicator.setImageTintList(ColorStateList.valueOf(Color.parseColor("#00ff00")));
                                    O_P_progress.setVisibility(View.VISIBLE);
                                    O_P_progress.setProgressBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#00ff00")));
                                    P_S_progress.setVisibility(View.VISIBLE);
                                    P_S_progress.setProgressBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#00ff00")));
                                    S_D_progress.setVisibility(View.VISIBLE);
                                    S_D_progress.setProgressBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#00ff00")));
                                    break;
                            }
                        }
                    }
                }

                int i = 0;
                for (MyOrderItemModel myOrderItemModel : DBqueries.myOrderItemModelList) {
                    if (i < 4) {
                        if (myOrderItemModel.getOrderStatus().equals("Delivered")) {
                            Glide.with(getContext()).load(myOrderItemModel.getProductImage()).apply(new RequestOptions().placeholder(R.mipmap.icon_placeholder)).into((CircleImageView) recentOrdersContainer.getChildAt(i));
                            i++;
                        }
                    } else {
                        break;
                    }
                }
                if (i == 0) {
                    yourRecentOrdersTitle.setText("No Recent Orders.");
                }
                if (i < 3) {
                    for (int x = i; x < 4; x++) {
                        recentOrdersContainer.getChildAt(x).setVisibility(View.GONE);
                    }
                }
                loadingDialog.show();
                loadingDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        loadingDialog.setOnDismissListener(null);
                        if (DBqueries.addressesModelList.size() == 0) {
                            addressName.setText("No Address");
                            address.setText("-");
                            pincode.setText("-");
                        } else {
                            setAddress();
                        }
                    }
                });
                DBqueries.loadAddresses(getContext(), loadingDialog, false);
            }
        });
        DBqueries.loadOrders(getContext(), null, loadingDialog);

        viewAllAddressBtn = view.findViewById(R.id.view_all_addresses_btn);
        viewAllAddressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myAddressesIntent = new Intent(getContext(), MyAddressesActivity.class);
                myAddressesIntent.putExtra("MODE", MANAGE_ADDRESS);
                startActivity(myAddressesIntent);
            }
        });

        signoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                DBqueries.clearData();
                Intent registerIntent = new Intent(getContext(), RegisterActivity.class);
                startActivity(registerIntent);
                getActivity().finish();
            }
        });

        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent updateUserInfo = new Intent(getContext(), UpdateUserInfoActivity.class);
                updateUserInfo.putExtra("Name", name.getText());
                updateUserInfo.putExtra("Email", email.getText());
                updateUserInfo.putExtra("Photo", DBqueries.profile);
                startActivity(updateUserInfo);
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        name.setText(DBqueries.userName);
        email.setText(DBqueries.email);
        if (!DBqueries.profile.equals("")) {
            Glide.with(getContext()).load(DBqueries.profile).apply(new RequestOptions().placeholder(R.mipmap.profile_placeholder)).into(profilView);
        } else {
            profilView.setImageResource(R.mipmap.profile_placeholder);
        }

        if (!loadingDialog.isShowing()) {
            if (DBqueries.addressesModelList.size() == 0) {
                addressName.setText("No Address");
                address.setText("-");
                pincode.setText("-");
            } else {
                setAddress();
            }
        }
    }


    private void setAddress() {
        String nametext, mobileNo;
        nametext = DBqueries.addressesModelList.get(DBqueries.selectedAddress).getName();
        mobileNo = DBqueries.addressesModelList.get(DBqueries.selectedAddress).getMobileNo();
        if (DBqueries.addressesModelList.get(DBqueries.selectedAddress).getAlternateMobileNo().equals("")) {
            addressName.setText(nametext + " - " + mobileNo);
        } else {
            addressName.setText(nametext + " - " + mobileNo + " or " + DBqueries.addressesModelList.get(DBqueries.selectedAddress).getAlternateMobileNo());
        }

        String flatNo = DBqueries.addressesModelList.get(DBqueries.selectedAddress).getFlatNo();
        String locality = DBqueries.addressesModelList.get(DBqueries.selectedAddress).getLocality();
        String landmark = DBqueries.addressesModelList.get(DBqueries.selectedAddress).getLandmark();
        String city = DBqueries.addressesModelList.get(DBqueries.selectedAddress).getCity();

        if (landmark.equals("")) {
            address.setText("Flat: " + flatNo + " Locality/Street: " + locality + " District: " + city);
        } else {
            address.setText("Flat: " + flatNo + " Locality/Street: " + locality + " Landmark: " + landmark + " District: " + city);
        }
        pincode.setText("Pincode: " + DBqueries.addressesModelList.get(DBqueries.selectedAddress).getPincode());
    }

}
