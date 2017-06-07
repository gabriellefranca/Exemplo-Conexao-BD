public class MainActivity extends Activity implements View.OnClickListener {

    public static final String LOGIN_URL = "http://seu url/login.php";

    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";

    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonLogin;

    private String username;
    private String password;

    Integer conectado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        buttonLogin = (Button) findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(this);
        verificaConexao();

    }

    private void userLogin() {
        username = editTextUsername.getText().toString().trim();
        password = editTextPassword.getText().toString().trim();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("successo")) {
                            //openProfile();
                            Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();
                            //Intent troca = new Intent(MainActivity.this,UserProfile.class);
                            //startActivity(troca);

                            Intent it = new Intent(MainActivity.this, UserProfile.class);
                            it.putExtra(KEY_USERNAME, username);
                            startActivity(it);

                        } else {
                            Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        verificaConexao();
                        if (conectado.toString().equals("0")) {
                            Toast.makeText(MainActivity.this, "Você não está conectado a Internet", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put(KEY_USERNAME, username);
                map.put(KEY_PASSWORD, password);
                return map;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private Toast toast;
    private long lastBackPressTime = 0;

    }

    @Override
    public void onClick(View v) {
        userLogin();
    }


}
