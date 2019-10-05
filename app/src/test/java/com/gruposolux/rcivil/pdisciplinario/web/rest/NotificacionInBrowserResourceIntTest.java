//package com.gruposolux.rcivil.pdisciplinario.web.rest;
//
//import com.gruposolux.rcivil.pdisciplinario.AppApp;
//
//import com.gruposolux.rcivil.pdisciplinario.domain.NotificacionInBrowser;
//import com.gruposolux.rcivil.pdisciplinario.repository.NotificacionInBrowserRepository;
//import com.gruposolux.rcivil.pdisciplinario.web.rest.errors.ExceptionTranslator;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
//import org.springframework.http.MediaType;
//import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.validation.Validator;
//
//import javax.persistence.EntityManager;
//import java.time.Instant;
//import java.time.temporal.ChronoUnit;
//import java.util.List;
//
//
//import static com.gruposolux.rcivil.pdisciplinario.web.rest.TestUtil.createFormattingConversionService;
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.hamcrest.Matchers.hasItem;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
///**
// * Test class for the NotificacionInBrowserResource REST controller.
// *
// * @see NotificacionInBrowserResource
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = AppApp.class)
//public class NotificacionInBrowserResourceIntTest {
//
//    private static final Instant DEFAULT_FECHA_CREADO = Instant.ofEpochMilli(0L);
//    private static final Instant UPDATED_FECHA_CREADO = Instant.now().truncatedTo(ChronoUnit.MILLIS);
//
//    private static final String DEFAULT_DESCRIPCION = "AAAAAAAAAA";
//    private static final String UPDATED_DESCRIPCION = "BBBBBBBBBB";
//
//    private static final Boolean DEFAULT_VISTO = false;
//    private static final Boolean UPDATED_VISTO = true;
//
//    @Autowired
//    private NotificacionInBrowserRepository notificacionInBrowserRepository;
//
//    @Autowired
//    private MappingJackson2HttpMessageConverter jacksonMessageConverter;
//
//    @Autowired
//    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;
//
//    @Autowired
//    private ExceptionTranslator exceptionTranslator;
//
//    @Autowired
//    private EntityManager em;
//
//    @Autowired
//    private Validator validator;
//
//    private MockMvc restNotificacionInBrowserMockMvc;
//
//    private NotificacionInBrowser notificacionInBrowser;
//
//    @Before
//    public void setup() {
//        MockitoAnnotations.initMocks(this);
//        final NotificacionInBrowserResource notificacionInBrowserResource = new NotificacionInBrowserResource(notificacionInBrowserRepository);
//        this.restNotificacionInBrowserMockMvc = MockMvcBuilders.standaloneSetup(notificacionInBrowserResource)
//            .setCustomArgumentResolvers(pageableArgumentResolver)
//            .setControllerAdvice(exceptionTranslator)
//            .setConversionService(createFormattingConversionService())
//            .setMessageConverters(jacksonMessageConverter)
//            .setValidator(validator).build();
//    }
//
//    /**
//     * Create an entity for this test.
//     *
//     * This is a static method, as tests for other entities might also need it,
//     * if they test an entity which requires the current entity.
//     */
//    public static NotificacionInBrowser createEntity(EntityManager em) {
//        NotificacionInBrowser notificacionInBrowser = new NotificacionInBrowser()
////            .CreatedAt(DEFAULT_FECHA_CREADO)
////            .contenido(DEFAULT_DESCRIPCION)
////            .visto(DEFAULT_VISTO);
//        return notificacionInBrowser;
//    }
//
//    @Before
//    public void initTest() {
//        notificacionInBrowser = createEntity(em);
//    }
//
//    @Test
//    @Transactional
//    public void createNotificacionInBrowser() throws Exception {
//        int databaseSizeBeforeCreate = notificacionInBrowserRepository.findAll().size();
//
//        // Create the NotificacionInBrowser
//        restNotificacionInBrowserMockMvc.perform(post("/api/notificacion-in-browsers")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(notificacionInBrowser)))
//            .andExpect(status().isCreated());
//
//        // Validate the NotificacionInBrowser in the database
//        List<NotificacionInBrowser> notificacionInBrowserList = notificacionInBrowserRepository.findAll();
//        assertThat(notificacionInBrowserList).hasSize(databaseSizeBeforeCreate + 1);
//        NotificacionInBrowser testNotificacionInBrowser = notificacionInBrowserList.get(notificacionInBrowserList.size() - 1);
//        assertThat(testNotificacionInBrowser.getCreatedAt()).isEqualTo(DEFAULT_FECHA_CREADO);
//        assertThat(testNotificacionInBrowser.getContenido()).isEqualTo(DEFAULT_DESCRIPCION);
//        //assertThat(testNotificacionInBrowser.isVisto()).isEqualTo(DEFAULT_VISTO);
//    }
//
//    @Test
//    @Transactional
//    public void createNotificacionInBrowserWithExistingId() throws Exception {
//        int databaseSizeBeforeCreate = notificacionInBrowserRepository.findAll().size();
//
//        // Create the NotificacionInBrowser with an existing ID
//        notificacionInBrowser.setId(1L);
//
//        // An entity with an existing ID cannot be created, so this API call must fail
//        restNotificacionInBrowserMockMvc.perform(post("/api/notificacion-in-browsers")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(notificacionInBrowser)))
//            .andExpect(status().isBadRequest());
//
//        // Validate the NotificacionInBrowser in the database
//        List<NotificacionInBrowser> notificacionInBrowserList = notificacionInBrowserRepository.findAll();
//        assertThat(notificacionInBrowserList).hasSize(databaseSizeBeforeCreate);
//    }
//
//    @Test
//    @Transactional
//    public void checkFechaCreadoIsRequired() throws Exception {
//        int databaseSizeBeforeTest = notificacionInBrowserRepository.findAll().size();
//        // set the field null
//        notificacionInBrowser.getCreatedAt(null);
//
//        // Create the NotificacionInBrowser, which fails.
//
//        restNotificacionInBrowserMockMvc.perform(post("/api/notificacion-in-browsers")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(notificacionInBrowser)))
//            .andExpect(status().isBadRequest());
//
//        List<NotificacionInBrowser> notificacionInBrowserList = notificacionInBrowserRepository.findAll();
//        assertThat(notificacionInBrowserList).hasSize(databaseSizeBeforeTest);
//    }
//
//    @Test
//    @Transactional
//    public void getAllNotificacionInBrowsers() throws Exception {
//        // Initialize the database
//        notificacionInBrowserRepository.saveAndFlush(notificacionInBrowser);
//
//        // Get all the notificacionInBrowserList
//        restNotificacionInBrowserMockMvc.perform(get("/api/notificacion-in-browsers?sort=id,desc"))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//            .andExpect(jsonPath("$.[*].id").value(hasItem(notificacionInBrowser.getId().intValue())))
//            .andExpect(jsonPath("$.[*].fechaCreado").value(hasItem(DEFAULT_FECHA_CREADO.toString())))
//            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION.toString())))
//            .andExpect(jsonPath("$.[*].visto").value(hasItem(DEFAULT_VISTO.booleanValue())));
//    }
//
//    @Test
//    @Transactional
//    public void getNotificacionInBrowser() throws Exception {
//        // Initialize the database
//        notificacionInBrowserRepository.saveAndFlush(notificacionInBrowser);
//
//        // Get the notificacionInBrowser
//        restNotificacionInBrowserMockMvc.perform(get("/api/notificacion-in-browsers/{id}", notificacionInBrowser.getId()))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//            .andExpect(jsonPath("$.id").value(notificacionInBrowser.getId().intValue()))
//            .andExpect(jsonPath("$.fechaCreado").value(DEFAULT_FECHA_CREADO.toString()))
//            .andExpect(jsonPath("$.descripcion").value(DEFAULT_DESCRIPCION.toString()))
//            .andExpect(jsonPath("$.visto").value(DEFAULT_VISTO.booleanValue()));
//    }
//
//    @Test
//    @Transactional
//    public void getNonExistingNotificacionInBrowser() throws Exception {
//        // Get the notificacionInBrowser
//        restNotificacionInBrowserMockMvc.perform(get("/api/notificacion-in-browsers/{id}", Long.MAX_VALUE))
//            .andExpect(status().isNotFound());
//    }
//
//    @Test
//    @Transactional
//    public void updateNotificacionInBrowser() throws Exception {
//        // Initialize the database
//        notificacionInBrowserRepository.saveAndFlush(notificacionInBrowser);
//
//        int databaseSizeBeforeUpdate = notificacionInBrowserRepository.findAll().size();
//
//        // Update the notificacionInBrowser
//        NotificacionInBrowser updatedNotificacionInBrowser = notificacionInBrowserRepository.findById(notificacionInBrowser.getId()).get();
//        // Disconnect from session so that the updates on updatedNotificacionInBrowser are not directly saved in db
//        em.detach(updatedNotificacionInBrowser);
//        updatedNotificacionInBrowser
//            .getCreatedAt(UPDATED_FECHA_CREADO)
//            .contenido(UPDATED_DESCRIPCION)
//            .visto(UPDATED_VISTO);
//
//        restNotificacionInBrowserMockMvc.perform(put("/api/notificacion-in-browsers")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(updatedNotificacionInBrowser)))
//            .andExpect(status().isOk());
//
//        // Validate the NotificacionInBrowser in the database
//        List<NotificacionInBrowser> notificacionInBrowserList = notificacionInBrowserRepository.findAll();
//        assertThat(notificacionInBrowserList).hasSize(databaseSizeBeforeUpdate);
//        NotificacionInBrowser testNotificacionInBrowser = notificacionInBrowserList.get(notificacionInBrowserList.size() - 1);
//        assertThat(testNotificacionInBrowser.getCreatedAt()).isEqualTo(UPDATED_FECHA_CREADO);
//        assertThat(testNotificacionInBrowser.getContenido()).isEqualTo(UPDATED_DESCRIPCION);
//      //  assertThat(testNotificacionInBrowser.isVisto()).isEqualTo(UPDATED_VISTO);
//    }
//
//    @Test
//    @Transactional
//    public void updateNonExistingNotificacionInBrowser() throws Exception {
//        int databaseSizeBeforeUpdate = notificacionInBrowserRepository.findAll().size();
//
//        // Create the NotificacionInBrowser
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restNotificacionInBrowserMockMvc.perform(put("/api/notificacion-in-browsers")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(notificacionInBrowser)))
//            .andExpect(status().isBadRequest());
//
//        // Validate the NotificacionInBrowser in the database
//        List<NotificacionInBrowser> notificacionInBrowserList = notificacionInBrowserRepository.findAll();
//        assertThat(notificacionInBrowserList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    public void deleteNotificacionInBrowser() throws Exception {
//        // Initialize the database
//        notificacionInBrowserRepository.saveAndFlush(notificacionInBrowser);
//
//        int databaseSizeBeforeDelete = notificacionInBrowserRepository.findAll().size();
//
//        // Get the notificacionInBrowser
//        restNotificacionInBrowserMockMvc.perform(delete("/api/notificacion-in-browsers/{id}", notificacionInBrowser.getId())
//            .accept(TestUtil.APPLICATION_JSON_UTF8))
//            .andExpect(status().isOk());
//
//        // Validate the database is empty
//        List<NotificacionInBrowser> notificacionInBrowserList = notificacionInBrowserRepository.findAll();
//        assertThat(notificacionInBrowserList).hasSize(databaseSizeBeforeDelete - 1);
//    }
//
//    @Test
//    @Transactional
//    public void equalsVerifier() throws Exception {
//        TestUtil.equalsVerifier(NotificacionInBrowser.class);
//        NotificacionInBrowser notificacionInBrowser1 = new NotificacionInBrowser();
//        notificacionInBrowser1.setId(1L);
//        NotificacionInBrowser notificacionInBrowser2 = new NotificacionInBrowser();
//        notificacionInBrowser2.setId(notificacionInBrowser1.getId());
//        assertThat(notificacionInBrowser1).isEqualTo(notificacionInBrowser2);
//        notificacionInBrowser2.setId(2L);
//        assertThat(notificacionInBrowser1).isNotEqualTo(notificacionInBrowser2);
//        notificacionInBrowser1.setId(null);
//        assertThat(notificacionInBrowser1).isNotEqualTo(notificacionInBrowser2);
//    }
//}
