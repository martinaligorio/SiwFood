package it.uniroma3.siwfood.authentication;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class AuthConfiguration{

    @Autowired
    private DataSource dataSource;

    /**
	 * Questo metodo definisce le query SQL per ottenere username e password
	 */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
            .dataSource(this.dataSource)
            //query per recuperare username e ruolo
            .authoritiesByUsernameQuery("SELECT username, role FROM credentials WHERE username=?")
            //query per username e password. Il flag boolean flag specifica se l'utente user è abilitato o no (va sempre a true)
            .usersByUsernameQuery("SELECT username, password, 1 as enabled FROM credentials WHERE username=?");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disabilita CSRF se necessario, o configuralo come richiesto
                .cors(cors -> cors.disable()) // Disabilita CORS se necessario, o configuralo come richiesto
                // AUTORIZZAZIONE: qui definiamo chi può accedere a cosa
                .authorizeHttpRequests( authorize -> authorize
                        // chiunque (autenticato o no) può accedere alle pagine index, login, register, ai css e alle immagini
                        .requestMatchers(HttpMethod.GET, "/", "/index", "/register", "/css/**", "/images/**", "favicon.ico").permitAll()
                        // chiunque (autenticato o no) può mandare richieste POST al punto di accesso per login e register
                        .requestMatchers(HttpMethod.POST, "/register", "/login").permitAll()
                        // solo gli utenti autenticati con ruolo ADMIN possono accedere a risorse con path /admin/**
                        .requestMatchers(HttpMethod.GET, "/admin/**").hasAnyAuthority("ADMIN_ROLE")
                        .requestMatchers(HttpMethod.POST, "/admin/**").hasAnyAuthority("ADMIN_ROLE")
                        // tutti gli utenti autenticati possono accere alle pag
                        .anyRequest().authenticated())
                        
                
                // LOGIN: qui definiamo come è gestita l'autenticazione
				// usiamo il protocollo formlogin       
                .formLogin(formLogin -> formLogin
                         // la pagina di login si trova a /login
                        .loginPage("/login")
                        .permitAll()
                        // se il login ha successo, si viene rediretti al path /default
                        .defaultSuccessUrl("/success", true)
                        .failureUrl("/login?error=true"))
                
                // LOGOUT: qui definiamo il logout
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .clearAuthentication(true).permitAll());

        
        return http.build();
    }
}
