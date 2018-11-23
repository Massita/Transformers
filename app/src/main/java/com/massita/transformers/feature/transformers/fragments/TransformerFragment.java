package com.massita.transformers.feature.transformers.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import com.massita.transformers.R;
import com.massita.transformers.api.RestClient;
import com.massita.transformers.api.model.Transformer;
import com.massita.transformers.util.SharedPreferencesRepository;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TransformerFragment extends Fragment implements TransformerFragmentContract.View {

    public static final String TRANSFORMER = "TRANSFORMER";
    public static final String TRANSFORMER_ACTION = "TRANSFORMER_ACTION";

    public static final int ACTION_NEW = 0;
    public static final int ACTION_EDIT = 1;
    public static final int ACTION_DELETE = 2;

    private TransformerFragmentContract.Presenter mPresenter;

    @BindView(R.id.name_input_text)
    EditText nameEditText;

    @BindView(R.id.team_switch)
    Switch teamSwitch;

    @BindView(R.id.strength_text_view)
    TextView strengthTextView;

    @BindView(R.id.strength_seekbar)
    SeekBar strengthSeekBar;

    @BindView(R.id.intelligence_text_view)
    TextView intelligenceTextView;

    @BindView(R.id.intelligence_seekbar)
    SeekBar intelligenceSeekBar;

    @BindView(R.id.speed_text_view)
    TextView speedTextView;

    @BindView(R.id.speed_seekbar)
    SeekBar speedSeekBar;

    @BindView(R.id.endurance_text_view)
    TextView enduranceTextView;

    @BindView(R.id.endurance_seekbar)
    SeekBar enduranceSeekBar;

    @BindView(R.id.rank_text_view)
    TextView rankTextView;

    @BindView(R.id.rank_seekbar)
    SeekBar rankSeekBar;

    @BindView(R.id.courage_text_view)
    TextView courageTextView;

    @BindView(R.id.courage_seekbar)
    SeekBar courageSeekBar;

    @BindView(R.id.firepower_text_view)
    TextView firepowerTextView;

    @BindView(R.id.firepower_seekbar)
    SeekBar firepowerSeekBar;

    @BindView(R.id.skill_text_view)
    TextView skillTextView;

    @BindView(R.id.skill_seekbar)
    SeekBar skillSeekBar;


    public static TransformerFragment newInstance() {
        return new TransformerFragment();
    }

    public static TransformerFragment newInstance(Transformer transformer) {
        TransformerFragment fragment = new TransformerFragment();
        Bundle b = new Bundle();
        b.putSerializable(TRANSFORMER, transformer);
        fragment.setArguments(b);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_transformer, container, false);
        ButterKnife.bind(this, view);

        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPresenter.start();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_transformer, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                mPresenter.deleteTransformer();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        SharedPreferences sharedPreferences = context.getSharedPreferences(SharedPreferencesRepository.SHARED_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferencesRepository sharedPreferencesRepository = new SharedPreferencesRepository(sharedPreferences);


        Transformer transformer = getArguments() != null ? (Transformer) getArguments().getSerializable(TRANSFORMER) : new Transformer();
        mPresenter = new TransformerFragmentPresenter(this, RestClient.getTransformersService(), sharedPreferencesRepository, transformer);
    }

    @OnClick(R.id.button_save)
    public void onSaveClick() {
        mPresenter.saveTransformer();
    }

    @Override
    public void setupNameListener() {
        nameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mPresenter.updateName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void setupTeamListener() {
        teamSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            mPresenter.updateTeam(isChecked);
        });
    }

    @Override
    public void setupStrengthListener() {
        strengthSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser) {
                    mPresenter.updateStrength(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public void setupIntelligenceListener() {
        intelligenceSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser) {
                    mPresenter.updateIntelligence(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public void setupEnduranceListener() {
        enduranceSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser) {
                    mPresenter.updateEndurance(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public void setupCourageListener() {
        courageSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mPresenter.updateCourage(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public void setupSpeedListener() {
        speedSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser) {
                    mPresenter.updateSpeed(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public void setupRankListener() {
        rankSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser) {
                    mPresenter.updateRank(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public void setupFirepowerListener() {
        firepowerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser) {
                    mPresenter.updateFirepower(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public void setupSkillListener() {
        skillSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser) {
                    mPresenter.updateSkill(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public void setName(String name) {
        nameEditText.setText(name);
    }

    @Override
    public void setTeam(boolean isAutobots) {
        teamSwitch.setChecked(isAutobots);
    }

    @Override
    public void setTeamText(boolean isAutobots) {
        if(isAutobots) {
            teamSwitch.setText(R.string.autobot);
        } else {
            teamSwitch.setText(R.string.decepticon);
        }
    }

    @Override
    public void setStrengthText(int value) {
        strengthTextView.setText(getString(R.string.strength_text, value));
    }

    @Override
    public void setStrengthValue(int value) {
        strengthSeekBar.setProgress(value);
    }

    @Override
    public void setIntelligenceText(int value) {
        intelligenceTextView.setText(getString(R.string.intelligence_text, value));
    }

    @Override
    public void setIntelligenceValue(int value) {
        intelligenceSeekBar.setProgress(value);
    }

    @Override
    public void setSpeedText(int value) {
        speedTextView.setText(getString(R.string.speed_text, value));
    }

    @Override
    public void setSpeedValue(int value) {
        speedSeekBar.setProgress(value);
    }

    @Override
    public void setEnduranceText(int value) {
        enduranceTextView.setText(getString(R.string.endurance_text, value));
    }

    @Override
    public void setEnduranceValue(int value) {
        enduranceSeekBar.setProgress(value);
    }

    @Override
    public void setRankText(int value) {
        rankTextView.setText(getString(R.string.rank_text, value));
    }

    @Override
    public void setRankValue(int value) {
        rankSeekBar.setProgress(value);
    }

    @Override
    public void setCourageText(int value) {
        courageTextView.setText(getString(R.string.courage_text, value));
    }

    @Override
    public void setCourageValue(int value) {
        courageSeekBar.setProgress(value);
    }

    @Override
    public void setFirepowerText(int value) {
        firepowerTextView.setText(getString(R.string.firepower_text, value));
    }

    @Override
    public void setFirepowerValue(int value) {
        firepowerSeekBar.setProgress(value);
    }

    @Override
    public void setSkillText(int value) {
        skillTextView.setText(getString(R.string.skill_text, value));
    }

    @Override
    public void setSkillValue(int value) {
        skillSeekBar.setProgress(value);
    }

    @Override
    public void finishWithSuccess(Transformer transformer, int action) {
        Intent result = new Intent();
        result.putExtra(TRANSFORMER, transformer);
        result.putExtra(TRANSFORMER_ACTION, action);
        getActivity().setResult(Activity.RESULT_OK, result);
        getActivity().finish();
    }

    @Override
    public void finishCanceled() {
        getActivity().finish();
    }
}
