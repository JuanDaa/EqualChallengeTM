package challenge.sports.com.equalchallengetm;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class Score_Board extends Activity {
    public static final String NOM_INS = "challenge.sports.com.score.Instantaneo";
    private static final String NOM_TERM ="challenge.sports.com.score.Terminado" ;
    private static final String NOM_POSP = "challenge.sports.com.score.Pospuesto";
    private ImageButton btnAscJug1, btnDescJug1, btnAscJug2, btnDescJug2, btnRestart;
    private ImageView imgSaqueJ1, imgSaqueJ2;
    private TextView txvPuntosJug1, txvSetsJug1, txvPuntosJug2, txvSetsJug2, txvGanador_1, txvGanador_2;
    private Button btnGuardarPartido, btnPosponerPartido;
    private boolean ganoJ1 = false;
    private boolean ganoJ2 = false;
    private boolean ganoJuegoJ1 = false;
    private boolean ganoJuegoj2 = false;
    private int turno =0;
    private int puntoJ1 = 0, setJ1 = 0, puntoJ2 = 0, setJ2 = 0, top = 11, min = 9, topSet = 3, minSet = 1,sumaPuntos=0;
    public SharedPreferences preferencesInstantaneo;
    public SharedPreferences.Editor editorInstantaneo;
    public SharedPreferences preferencesTerminado;
    public SharedPreferences.Editor editorTerminado;
    public SharedPreferences preferencesPospuesto;
    public SharedPreferences.Editor editorPospuesto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score__board);


        preferencesInstantaneo = getSharedPreferences(NOM_INS, MODE_PRIVATE);
        editorInstantaneo = preferencesInstantaneo.edit();

        preferencesTerminado = getSharedPreferences(NOM_TERM, MODE_PRIVATE);
        editorTerminado = preferencesTerminado.edit();

        preferencesPospuesto = getSharedPreferences(NOM_POSP, MODE_PRIVATE);
        editorPospuesto = preferencesPospuesto.edit();


        editorInstantaneo.putInt("puntoJ1", 0);
        editorInstantaneo.putInt("puntoJ2", 0);
        editorInstantaneo.putInt("ResultadoJ1", 0);
        editorInstantaneo.putInt("ResultadoJ2", 0);
        editorInstantaneo.putInt("SetJ1", 0);
        editorInstantaneo.putInt("SetJ2", 0);
        editorInstantaneo.putBoolean("ServicioJ1", false);
        editorInstantaneo.putBoolean("ServicioJ2", false);
        editorInstantaneo.commit();

        if (preferencesInstantaneo.getBoolean("ServicioJ1", false) == false && preferencesInstantaneo.getBoolean("ServicioJ2", false) == false) {
            Intent elegir = new Intent(Score_Board.this, ElegirServicio.class);
            startActivity(elegir);
        }
        turno = 0;
    }
        //<editor-fold desc="Instancias">
    private void instancias() {

        if (preferencesInstantaneo.getBoolean("ServicioJ1", true) == true && preferencesInstantaneo.getBoolean("ServicioJ2", false) == false || preferencesInstantaneo.getBoolean("ServicioJ1", false) == false && preferencesInstantaneo.getBoolean("ServicioJ2", true) == true) {
            imgSaqueJ1 = (ImageView) findViewById(R.id.imgSaqueJ1);
            imgSaqueJ2 = (ImageView) findViewById(R.id.imgSaqueJ2);
            if (preferencesInstantaneo.getBoolean("ServicioJ1", true) == true && preferencesInstantaneo.getBoolean("ServicioJ2", false) == false) {
                imgSaqueJ1.setVisibility(View.VISIBLE);
                imgSaqueJ2.setVisibility(View.INVISIBLE);
                turno = 1;
            } else if (preferencesInstantaneo.getBoolean("ServicioJ2", true) == true && preferencesInstantaneo.getBoolean("ServicioJ1", false) == false) {
                imgSaqueJ2.setVisibility(View.VISIBLE);
                imgSaqueJ1.setVisibility(View.INVISIBLE);
                turno = 2
                ;
            }

            txvPuntosJug1 = (TextView) findViewById(R.id.txv_puntos_jug1);
            txvSetsJug1 = (TextView) findViewById(R.id.txv_sets_jug1);
            txvPuntosJug2 = (TextView) findViewById(R.id.txv_puntos_jug2);
            txvSetsJug2 = (TextView) findViewById(R.id.txv_sets_jug2);
            txvGanador_1 = (TextView) findViewById(R.id.txvGanador_1);
            ;
            txvGanador_2 = (TextView) findViewById(R.id.txvGanador_2);

            btnGuardarPartido = (Button) findViewById(R.id.btnGuardarPartido);
            btnGuardarPartido.setEnabled(false);

            btnPosponerPartido = (Button) findViewById(R.id.btnPosponerPartido);
            btnPosponerPartido.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder alerta = new AlertDialog.Builder(Score_Board.this);
                    alerta.setTitle("POSPONER").setMessage("Seguro que desea posponer el partido")
                            .setNegativeButton("No",null)
                            .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    editorPospuesto.putInt("puntoJ1", puntoJ1);
                                    editorPospuesto.putInt("puntoJ2", puntoJ2);
                                    editorPospuesto.putInt("SetJ1", setJ1);
                                    editorPospuesto.putInt("SetJ2", setJ2);
                                    editorPospuesto.commit();

                                    finish();
                                }
                            }).create();
                    alerta.show();
                }
            });

            btnAscJug1 = (ImageButton) findViewById(R.id.btn_asc_jug1);
            btnAscJug1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    accionSubirJ1();
                    ganoSet(view);
                    ganoJuego(view);

                }
            });

            btnDescJug1 = (ImageButton) findViewById(R.id.btn_desc_jug1);
            btnDescJug1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    accionBajarj1();
                    ganoSet(view);
                    ganoJuego(view);
                }
            });
            btnAscJug2 = (ImageButton) findViewById(R.id.btn_asc_jug2);
            btnAscJug2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    accionSubirJ2();
                    ganoSet(view);
                    ganoJuego(view);
                }
            });
            btnDescJug2 = (ImageButton) findViewById(R.id.btn_desc_jug2);
            btnDescJug2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    acionBajarj2();
                    ganoSet(view);
                    ganoJuego(view);
                }
            });


            btnRestart = (ImageButton) findViewById(R.id.btn_restart);
            btnRestart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (setJ1 == 0 && setJ2 == 0 && puntoJ1 == 0 && puntoJ2 == 0) {

                    } else {
                        AlertDialog.Builder alerta = new AlertDialog.Builder(Score_Board.this);
                        alerta.setTitle("Reiniciar").setMessage("¿Esta seguro(a) que desea reiniciar el Juego?").setNegativeButton("No", null)
                                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                        puntoJ1 = 0;
                                        setJ1 = 0;
                                        puntoJ2 = 0;
                                        setJ2 = 0;
                                        top = 11;
                                        min = 9;
                                        topSet = 3;
                                        minSet = 1;
                                        editorInstantaneo.putInt("puntoJ1", 0);
                                        editorInstantaneo.putInt("puntoJ2", 0);
                                        editorInstantaneo.putInt("ResultadoJ1", 0);
                                        editorInstantaneo.putInt("ResultadoJ2", 0);
                                        editorInstantaneo.putInt("SetJ1", 0);
                                        editorInstantaneo.putInt("SetJ2", 0);
                                        editorInstantaneo.putBoolean("ServicioJ1", false);
                                        editorInstantaneo.putBoolean("ServicioJ2", false);
                                        editorInstantaneo.commit();
                                        txvPuntosJug1.setText("" + puntoJ1);
                                        txvPuntosJug2.setText("" + puntoJ2);
                                        txvSetsJug1.setText("" + setJ1);
                                        txvSetsJug2.setText("" + setJ2);
                                        turno = 0;
                                        if (preferencesInstantaneo.getBoolean("ServicioJ1", false) == false && preferencesInstantaneo.getBoolean("ServicioJ2", false) == false) {
                                            Intent elegir = new Intent(Score_Board.this, ElegirServicio.class);
                                            startActivity(elegir);

                                        }
                                    }
                                }).create()
                        ;
                        alerta.show();
                    }
                }
            });
        }

    }


    @Override
    protected void onResume() {
        if (preferencesInstantaneo.getBoolean("ServicioJ1", true) == true && preferencesInstantaneo.getBoolean("ServicioJ2", true) == true) {
            editorInstantaneo.putBoolean("ServicioJ1", false);
            editorInstantaneo.putBoolean("ServicioJ2", false);
            editorInstantaneo.commit();
            Intent elegir = new Intent(Score_Board.this, ElegirServicio.class);
            startActivity(elegir);
        } else {
            instancias();
        }

        super.onResume();
    }
    //</editor-fold>

        //<editor-fold desc="Accion botones de jugador 1">
    private void accionSubirJ1() {
        puntoJ1++;
        txvPuntosJug1.setText("" + puntoJ1);
        editorInstantaneo.putInt("puntoJ1",puntoJ1 );
        editorInstantaneo.commit();
        cambioSaque();
        validacionGanadorSet(puntoJ1, puntoJ2);

    }
    private void accionBajarj1() {
        if (puntoJ1 > 0) {
            puntoJ1--;
            txvPuntosJug1.setText("" + puntoJ1);
            editorInstantaneo.putInt("puntoJ1",puntoJ1 );
            editorInstantaneo.commit();
            cambioSaque();
        } else {
            String nombre = "jugador 1";
            validacionCeros(nombre);
        }
        if (puntoJ1 >= 9 && puntoJ2 >= 9) {
            if (puntoJ1<=puntoJ2-1) {
                top  --;
                min --;
               Log.d("topin", String.valueOf(top+"--"+min));

            }

            if (puntoJ1==puntoJ2-2) {
                top = puntoJ2;
                min = puntoJ1;
                Log.d("topin", String.valueOf(top+"--"+min));
                if (top == puntoJ2 && min == puntoJ1) {
                    editorInstantaneo.putInt("ResultadoJ1",puntoJ1);
                    editorInstantaneo.putInt("ResultadoJ2",puntoJ2 );
                    puntoJ2 = 1;
                    puntoJ1 = 1;
                    editorInstantaneo.putInt("puntoJ1",0 );
                    editorInstantaneo.putInt("puntoJ2",0 );
                    editorInstantaneo.commit();
                    txvPuntosJug2.setText("0");
                    txvPuntosJug1.setText("0");
                    txvSetsJug2.setText("" + (setJ2+1));
                    setJ2++;
                    top = 11;
                    min = 9;
                    ganoJ2=true;

                }
            }
        }
    }


    //</editor-fold>

        //<editor-fold desc="Accion botones de jugador 2">
    private void accionSubirJ2() {
        puntoJ2++;
        txvPuntosJug2.setText("" + puntoJ2);
        editorInstantaneo.putInt("puntoJ2",puntoJ2 );
        editorInstantaneo.commit();
        cambioSaque();
        validacionGanadorSet(puntoJ2, puntoJ1);
    }

    private void acionBajarj2() {
        if (puntoJ2 > 0) {
            puntoJ2--;
            txvPuntosJug2.setText("" + puntoJ2);
            editorInstantaneo.putInt("puntoJ2",puntoJ2 );
            editorInstantaneo.commit();
            cambioSaque();
        } else {

            String nombre = "jugador 2";
            validacionCeros(nombre);
        }

        if (puntoJ2>=9 && puntoJ1>=9) {

            if (puntoJ2<=puntoJ1-1) {
                top  --;
                min --;

            }

            if (puntoJ2==puntoJ1-2) {
                top = puntoJ1;
                min = puntoJ2;
                //  Log.d("topin", String.valueOf(top+"--"+min));
                if (top == puntoJ1 && min == puntoJ2) {
                    editorInstantaneo.putInt("ResultadoJ1",puntoJ1);
                    editorInstantaneo.putInt("ResultadoJ2",puntoJ2);
                    puntoJ1 = 1;
                    puntoJ2 = 1;
                    editorInstantaneo.putInt("puntoJ1",0 );
                    editorInstantaneo.putInt("puntoJ2",0 );
                    editorInstantaneo.commit();
                    txvPuntosJug1.setText("0");
                    txvPuntosJug2.setText("0");
                    txvSetsJug1.setText("" + (setJ1+1));
                    setJ1++;
                    top = 11;
                    min = 9;
                        ganoJ1=true;
                }

            }

        }
    }
    //</editor-fold>

        //<editor-fold desc="Validaciones">

    private void validacionGanadorSet(int j1, int j2) {
//Condicion que valida la diferencia de +2 necesaria para ganar
        if (j1 == top && j2 <= min) {
            if (puntoJ1==top) {
                editorInstantaneo.putInt("ResultadoJ1",puntoJ1);
                editorInstantaneo.putInt("ResultadoJ2",puntoJ2 );
                puntoJ1 = 0;
                puntoJ2 = 0;
                editorInstantaneo.putInt("puntoJ1",0 );
                editorInstantaneo.putInt("puntoJ2",0 );
                editorInstantaneo.commit();
                txvPuntosJug1.setText("0");
                txvPuntosJug2.setText("0");
                txvSetsJug1.setText("" + (setJ1+1));
                setJ1++;
                editorInstantaneo.putInt("SetJ1",setJ1);
                editorInstantaneo.putInt("SetJ2",setJ2);
                editorInstantaneo.commit();
                top = 11;
                min = 9;
                    ganoJ1=true;
                    validacionGanadorJuego(setJ1,setJ2);
            } else if (puntoJ2==top) {
                editorInstantaneo.putInt("ResultadoJ1",puntoJ1);
                editorInstantaneo.putInt("ResultadoJ2",puntoJ2);
                puntoJ2 = 0;
                puntoJ1 = 0;
                editorInstantaneo.putInt("puntoJ1",0 );
                editorInstantaneo.putInt("puntoJ2",0 );
                editorInstantaneo.commit();
                txvPuntosJug2.setText("0");
                txvPuntosJug1.setText("0");
                txvSetsJug2.setText("" + (setJ2+1));
                setJ2++;
                editorInstantaneo.putInt("SetJ1",setJ1);
                editorInstantaneo.putInt("SetJ2",setJ2);
                editorInstantaneo.commit();
                top = 11;
                min = 9;
                     ganoJ2=true;
                     validacionGanadorJuego(setJ2,setJ1);

            }
        } else if (j1>9 && j2 > 9) {
            if (j1 == j2) {
                top++;
                min++;
              //  Log.d("topin", String.valueOf(top+"--"+min));
            }
        }
    }
    private void validacionGanadorJuego(int set1, int set2) {

        if (set1 == topSet && set2 <= minSet) {
            if (setJ1==topSet) {
                editorInstantaneo.putInt("SetJ1",setJ1);
                editorInstantaneo.putInt("SetJ2",setJ2);
                editorTerminado.putInt("ResultadoJ1",setJ1);
                editorTerminado.putInt("ResultadoJ2",setJ2);
                editorInstantaneo.commit();
                String getPunto1= String.valueOf(preferencesInstantaneo.getInt("ResultadoJ1",0));
                String getPunto2= String.valueOf(preferencesInstantaneo.getInt("ResultadoJ2",0));
                txvPuntosJug1.setText(getPunto1);
                txvPuntosJug2.setText(getPunto2);
                String nombre = "Jugador 1";
                alertaGanadorJuego(nombre);
                btnAscJug1.setEnabled(false);
                btnDescJug1.setEnabled(false);
                btnAscJug2.setEnabled(false);
                btnDescJug2.setEnabled(false);
                btnGuardarPartido.setFocusable(true);
                btnPosponerPartido.setEnabled(false);
                ganoJuegoJ1 = true;

              /*  setJ1 = 0;
                setJ2 = 0;
                topSet = 3;
                minSet = 1;
                top = 11;
                min = 9;*/
            } else if (setJ2==topSet) {
                editorInstantaneo.putInt("SetJ1",setJ1);
                editorInstantaneo.putInt("SetJ2",setJ2);
                editorTerminado.putInt("ResultadoJ1",setJ1);
                editorTerminado.putInt("ResultadoJ2",setJ2);
                editorInstantaneo.commit();
                String getPunto1= String.valueOf(preferencesInstantaneo.getInt("ResultadoJ1",0));
                String getPunto2= String.valueOf(preferencesInstantaneo.getInt("ResultadoJ2",0));
                txvPuntosJug1.setText(getPunto1);
                txvPuntosJug2.setText(getPunto2);
                String nombre = "Jugador 2";
                alertaGanadorJuego(nombre);
                btnAscJug1.setEnabled(false);
                btnDescJug1.setEnabled(false);
                btnAscJug2.setEnabled(false);
                btnDescJug2.setEnabled(false);
                btnGuardarPartido.setFocusable(true);
                btnPosponerPartido.setEnabled(false);
                ganoJuegoj2 = true;
             /*   setJ2 = 0;
                setJ1 = 0;
                topSet = 3;
                minSet = 1;
                top = 11;
                min = 9;*/
            }

        }else if (set1>=2 && set2 >=2) {
            if (set1 == set2) {
                topSet++;
                minSet++;
                Log.d("topin", String.valueOf(topSet+"--"+minSet));
            }
        } else if (set1<2 && set2 <2) {
            topSet = 3;
            minSet=1;
            Log.d("topin", String.valueOf(topSet+"--"+minSet));
        }


    }
    private void validacionBajarSet(int set1, int set2) {

        // if (setJ1 >= 2 && setJ2 >= 2) {
        if (set2 == set1 - 1) {
            topSet--;
            minSet--;
            Log.d("topin", String.valueOf(topSet + "--" + minSet));
        } else if (set1 == set2 - 1) {
            topSet--;
            minSet--;
            Log.d("topin", String.valueOf(topSet + "--" + minSet));
        } else if (set1 < 2 && set2 < 2) {
            topSet = 3;
            minSet = 1;
            Log.d("topin", String.valueOf(topSet + "--" + minSet));
        }

        if (setJ1 >= 2 && setJ2 >= 2) {
            if (setJ1 == setJ2 - 2) {
                topSet = setJ2;
                minSet = setJ1;
                if (topSet == setJ2 && minSet == setJ1) {
                    editorInstantaneo.putInt("SetJ1",setJ1);
                    editorInstantaneo.putInt("SetJ2",setJ2);
                    editorTerminado.putInt("ResultadoJ1",setJ1);
                    editorTerminado.putInt("ResultadoJ2",setJ2);
                    editorInstantaneo.commit();
                    String getPunto1= String.valueOf(preferencesInstantaneo.getInt("ResultadoJ1",0));
                    String getPunto2= String.valueOf(preferencesInstantaneo.getInt("ResultadoJ2",0));
                    txvPuntosJug1.setText(getPunto1);
                    txvPuntosJug2.setText(getPunto2);
                    String nombre = "Jugador 2";
                    alertaGanadorJuego(nombre);
                    btnAscJug1.setEnabled(false);
                    btnDescJug1.setEnabled(false);
                    btnAscJug2.setEnabled(false);
                    btnDescJug2.setEnabled(false);
                    btnGuardarPartido.setFocusable(true);
                    btnPosponerPartido.setEnabled(false);
                    ganoJuegoj2 = true;
                      /*  setJ1 = 0;
                        setJ2 = 0;
                        topSet = 3;
                        minSet = 1;
                        top = 11;
                        min = 9;*/
                }
            } else if (setJ2 == setJ1 - 2) {
                topSet = setJ1;
                minSet = setJ2;
                if (topSet == setJ1 && minSet == setJ2) {
                    editorInstantaneo.putInt("SetJ1",setJ1);
                    editorInstantaneo.putInt("SetJ2",setJ2);
                    editorTerminado.putInt("ResultadoJ1",setJ1);
                    editorTerminado.putInt("ResultadoJ2",setJ2);
                    editorInstantaneo.commit();
                    String getPunto1= String.valueOf(preferencesInstantaneo.getInt("ResultadoJ1",0));
                    String getPunto2= String.valueOf(preferencesInstantaneo.getInt("ResultadoJ2",0));
                    txvPuntosJug1.setText(getPunto1);
                    txvPuntosJug2.setText(getPunto2);
                    String nombre = "Jugador 1";
                    alertaGanadorJuego(nombre);
                    btnAscJug1.setEnabled(false);
                    btnDescJug1.setEnabled(false);
                    btnAscJug2.setEnabled(false);
                    btnDescJug2.setEnabled(false);
                    btnGuardarPartido.setFocusable(true);
                    btnPosponerPartido.setEnabled(false);
                    ganoJuegoJ1 = true;
                   /* setJ1 = 0;
                    setJ2 = 0;
                    topSet = 3;
                    minSet = 1;
                    top = 11;
                    min = 9;*/
                }
            }
        }
    }

    private void validacionCeros(String nombre) {
        if (nombre == "jugador 1") {
            if (setJ1 == 0 && setJ2 == 0) {
                //No hacer nada
            } else if (puntoJ1 <= 0 && setJ1 >= 1) {

                quitarSets(nombre);
            }
        } else if (nombre == "jugador 2") {
            if (setJ1 == 0 && setJ2 == 0) {
                //No hacer nada
            } else if (puntoJ2 <= 0 && setJ2 >= 1) {
                quitarSets(nombre);
            }
        }
    }
    //</editor-fold>

        //<editor-fold desc="Acciones">


    private void cambioSaque() {
        sumaPuntos = puntoJ1 + puntoJ2;
        if (sumaPuntos % 2 == 0) {
            if (turno == 1) {
                imgSaqueJ1.setVisibility(View.INVISIBLE);
                imgSaqueJ2.setVisibility(View.VISIBLE);
                turno = 2;
            } else if (turno==2) {
                imgSaqueJ1.setVisibility(View.VISIBLE);
                imgSaqueJ2.setVisibility(View.INVISIBLE);
                turno = 1;
            }
        }
    }

    private void quitarSets(final String nombre) {
        AlertDialog.Builder alerta = new AlertDialog.Builder(Score_Board.this);
        alerta.setTitle("Decrementear").setMessage("Desea bajar un set a "+nombre)
            .setNegativeButton("No",null)
            .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String a = nombre;
                if (nombre=="jugador 1") {
                    setJ1--;
                    txvSetsJug1.setText("" + setJ1);
                    validacionBajarSet(setJ1,setJ2);
                } else if (nombre == "jugador 2") {
                    setJ2--;
                    txvSetsJug2.setText("" + setJ2);
                    validacionBajarSet(setJ2,setJ1);
                }

            }
        }).create();
        alerta.show();
    }
    private void alertaGanadorJuego(String nomJugador) {
        AlertDialog.Builder alerta = new AlertDialog.Builder(Score_Board.this);
        alerta.setTitle("Partido terminado").setMessage("Partido ganado por "+nomJugador).create();
        alerta.show();
    }

    private void ganoSet(View view) {
        if (ganoJ1) {
            Snackbar snackbar = Snackbar.make(view, "Set Ganado por Jugador 1", Snackbar.LENGTH_LONG)
                    .setAction("Ok", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ganoJ1 = false;
                        }
                    });
            View snackBarView = snackbar.getView();
            TextView tv = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
            tv.setTextColor(Color.WHITE);
            snackbar.show();

            ganoJ1=false;
        } else if (ganoJ2) {
            Snackbar snackbar = Snackbar.make(view, "Set Ganado por Jugador 2", Snackbar.LENGTH_LONG)
                    .setAction("Ok", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ganoJ2 = false;
                        }
                    });
            View snackBarView = snackbar.getView();
            TextView tv = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
            tv.setTextColor(Color.WHITE);
            snackbar.show();
            ganoJ2=false;
        } else {
            ganoJ1 = false;
            ganoJ2 = false;
        }
    }
    private void ganoJuego(View view) {
        if (ganoJuegoJ1) {
            txvGanador_1.setVisibility(view.VISIBLE);
            txvGanador_1.setTextColor(Color.GREEN);
            txvGanador_1.setText("GANADOR");
            btnGuardarPartido.setEnabled(true);
            txvGanador_2.setVisibility(view.VISIBLE);
            txvGanador_2.setTextColor(Color.RED);
            txvGanador_2.setText("PERDEDOR");
            btnRestart.setEnabled(false);
            imgSaqueJ1.setVisibility(View.INVISIBLE);
            imgSaqueJ2.setVisibility(View.INVISIBLE);

            btnGuardarPartido.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        } else if (ganoJuegoj2) {
            txvGanador_2.setVisibility(view.VISIBLE);
            txvGanador_2.setTextColor(Color.GREEN);
            txvGanador_2.setText("GANADOR");
            btnGuardarPartido.setEnabled(true);
            txvGanador_1.setVisibility(view.VISIBLE);
            txvGanador_1.setTextColor(Color.RED);
            txvGanador_1.setText("PERDEDOR");
            btnRestart.setEnabled(false);
            imgSaqueJ1.setVisibility(View.INVISIBLE);
            imgSaqueJ2.setVisibility(View.INVISIBLE);
            AlertDialog.Builder alerta = new AlertDialog.Builder(Score_Board.this);
            btnGuardarPartido.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder alerta = new AlertDialog.Builder(Score_Board.this);
                    alerta.setTitle("Datos guardados correctamente").setMessage("Gracias señor(a) Juez por su labor")
                            .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    finish();
                                }
                            }).create();
                    alerta.show();
                }
            });
        } else {
            ganoJuegoJ1 = false;
            ganoJuegoj2 = false;
        }
    }
    //</editor-fold>

    }
