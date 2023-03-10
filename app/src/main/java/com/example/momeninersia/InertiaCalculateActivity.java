package com.example.momeninersia;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.momeninersia.databinding.ActivityInertiaCalculateBinding;
import com.example.momeninersia.databinding.LayoutSuccessDialogBinding;

public class InertiaCalculateActivity extends AppCompatActivity {
    private ActivityInertiaCalculateBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInertiaCalculateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        float coefficient = 1F;

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getResources().getString(R.string.tv_app));
        }

        if (getIntent() != null){
            String title = getIntent().getStringExtra("title");
            binding.tvCalculateTitle.setText(title);

            switch (title) {
                case "Solid Sphere":
                case "Bola Pejal":
                    coefficient = 2 / 5F;
                    break;
                case "Solid Cylinder":
                case "Silinder Pejal":
                    coefficient = 1 / 2F;
                    break;
                case "Hollow Sphere":
                case "Bola Berongga":
                    coefficient = 2 / 3F;
                    break;
            }
        }

        float finalCoefficient = coefficient;
        binding.btnCalculateCalculate.setOnClickListener(view -> {
            if ((binding.edtDistance.getText() != null
                    && binding.edtDistance.getText().toString().trim().isEmpty())
                    || (binding.edtMass.getText() != null
                    && binding.edtMass.getText().toString().trim().isEmpty())){
                Toast.makeText(this, getResources().getString(R.string.form_empty),
                        Toast.LENGTH_SHORT).show();
            }else{
                float distance = Float.parseFloat(binding.edtDistance.getText().toString().trim());
                float mass = Float.parseFloat(binding.edtMass.getText().toString().trim());
                float result = finalCoefficient * mass * distance * distance;
                showSuccessDialog(result);
            }
        });
    }

    private void showSuccessDialog(float result){
        AlertDialog.Builder builder = new AlertDialog.Builder(InertiaCalculateActivity.this,R.style.AlertDialogTheme);
        LayoutSuccessDialogBinding dialogBinding = LayoutSuccessDialogBinding.inflate(getLayoutInflater());
        builder.setView(dialogBinding.getRoot());

        dialogBinding.textTitle.setText(getResources().getString(R.string.tv_success));
        dialogBinding.textMessage.setText(Float.toString(result));
        dialogBinding.buttonAction.setText(getResources().getString(R.string.tv_okay));
        dialogBinding.imageIcon.setImageResource(R.drawable.ic_done);

        final AlertDialog alertDialog = builder.create();

        dialogBinding.buttonAction.setOnClickListener(view -> {
            alertDialog.dismiss();
        });

        if (alertDialog.getWindow() != null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();
    }
}