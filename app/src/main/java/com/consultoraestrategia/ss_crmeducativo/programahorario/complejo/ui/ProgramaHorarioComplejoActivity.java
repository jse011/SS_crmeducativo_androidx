package com.consultoraestrategia.ss_crmeducativo.programahorario.complejo.ui;

import android.content.Context;
import android.content.Intent;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.bundle.CRMBundle;
import com.consultoraestrategia.ss_crmeducativo.programahorario.ProgramaHorarioPresenter;
import com.consultoraestrategia.ss_crmeducativo.programahorario.complejo.ProgramaHorarioComplejoPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.programahorario.data.source.ProgramaHorarioRepository;
import com.consultoraestrategia.ss_crmeducativo.programahorario.data.source.local.ProgramaHorarioLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.programahorario.domain.usecase.GetCursoCompletos;
import com.consultoraestrategia.ss_crmeducativo.programahorario.domain.usecase.GetProgramaHorarioCompleto;
import com.consultoraestrategia.ss_crmeducativo.programahorario.domain.usecase.GetReporteHorario;
import com.consultoraestrategia.ss_crmeducativo.programahorario.ui.ProgramaHorarioActivity;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;
import com.evrencoskun.tableview.TableView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProgramaHorarioComplejoActivity extends ProgramaHorarioActivity {
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.toolbar_horario)
    Toolbar toolbarHorario;
    @BindView(R.id.rc_programa_horario)
    RecyclerView rcProgramaHorario;
    @BindView(R.id.horario)
    TableView horario;
    @BindView(R.id.rc_cursos)
    RecyclerView rcCursos;
    @BindView(R.id.textvacio)
    TextView textvacio;
    public static void launchHorarioActivity(Context context, CRMBundle crmBundle) {
        Intent intent = new Intent(context, ProgramaHorarioComplejoActivity.class);
        intent.putExtras(crmBundle.instanceBundle());
        context.startActivity(intent);
    }

    @Override
    protected ProgramaHorarioPresenter getPresenter() {

        ProgramaHorarioRepository programaHorarioRepository = ProgramaHorarioRepository.getInstance(
                new ProgramaHorarioLocalDataSource(
                        InjectorUtils.provideHorarioProgramaDao(),
                        InjectorUtils.provideDiaDao(),
                        InjectorUtils.provideCursoDao(),
                        InjectorUtils.provideCargaCursoDao(),
                        InjectorUtils.provideParametrosDisenioDao()));

        return new ProgramaHorarioComplejoPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources(),
                new GetProgramaHorarioCompleto(programaHorarioRepository),
                new GetReporteHorario(programaHorarioRepository),
                new GetCursoCompletos(programaHorarioRepository));
    }

    @Override
    protected RecyclerView getRcCurso() {
        return rcCursos;
    }

    @Override
    protected RecyclerView getRcProgramaHorario() {
        return rcProgramaHorario;
    }

    @Override
    protected TableView getHorario() {
        return horario;
    }

    @Override
    protected ProgressBar getProgress() {
        return progressBar;
    }

    @Override
    protected Toolbar getToolbar() {
        return toolbarHorario;
    }

    @Override
    protected void setContentViewFind() {
        setContentView(R.layout.horario_activity_v2);
        ButterKnife.bind(this);
    }

    @Override
    protected void hideTextEmpty() {
        textvacio.setVisibility(View.GONE);
    }

    @Override
    public void showTextEmpty() {
        textvacio.setVisibility(View.VISIBLE);
    }
}
