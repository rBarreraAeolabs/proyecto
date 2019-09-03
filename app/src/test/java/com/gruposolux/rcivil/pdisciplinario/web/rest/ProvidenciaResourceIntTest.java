//package com.gruposolux.rcivil.pdisciplinario.web.rest;
//
//import com.gruposolux.rcivil.pdisciplinario.AppApp;
//
//import com.gruposolux.rcivil.pdisciplinario.domain.Providencia;
//import com.gruposolux.rcivil.pdisciplinario.repository.ProvidenciaRepository;
//import com.gruposolux.rcivil.pdisciplinario.service.ProvidenciaService;
//import com.gruposolux.rcivil.pdisciplinario.service.dto.ProvidenciaDTO;
//import com.gruposolux.rcivil.pdisciplinario.service.mapper.ProvidenciaMapper;
//import com.gruposolux.rcivil.pdisciplinario.web.rest.errors.ExceptionTranslator;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
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
//import java.time.LocalDate;
//import java.time.Instant;
//import java.time.ZoneId;
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
//import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.EstadoProvidencia;
///**
// * Test class for the ProvidenciaResource REST controller.
// *
// * @see ProvidenciaResource
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = AppApp.class)
//public class ProvidenciaResourceIntTest {
//
//    private static final String DEFAULT_DESCRIPCION = "AAAAAAAAAA";
//    private static final String UPDATED_DESCRIPCION = "BBBBBBBBBB";
//
//    private static final Instant DEFAULT_FECHA_CREADO = Instant.ofEpochMilli(0L);
//    private static final LocalDate UPDATED_FECHA_CREADO = LocalDate.now(ZoneId.systemDefault());
//
//    private static final Instant DEFAULT_FECHA_INGRESO = Instant.ofEpochMilli(0L);
//    private static final Instant UPDATED_FECHA_INGRESO = Instant.now().truncatedTo(ChronoUnit.MILLIS);
//
//    private static final EstadoProvidencia DEFAULT_ESTADO = EstadoProvidencia.ESTADO_1;
//    private static final EstadoProvidencia UPDATED_ESTADO = EstadoProvidencia.ESTADO_2;
//
//    @Autowired
//    private ProvidenciaRepository providenciaRepository;
//
//    @Autowired
//    private ProvidenciaMapper providenciaMapper;
//
//    @Autowired
//    private ProvidenciaService providenciaService;
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
//    @Qualifier("defaultValidator")
//    @Autowired
//    private Validator validator;
//
//    private MockMvc restProvidenciaMockMvc;
//
//    private Providencia providencia;
//
//    @Before
//    public void setup() {
//        MockitoAnnotations.initMocks(this);
//        final ProvidenciaResource providenciaResource = new ProvidenciaResource(providenciaService, fileSystemStorageService);
//        this.restProvidenciaMockMvc = MockMvcBuilders.standaloneSetup(providenciaResource)
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
//    public static Providencia createEntity(EntityManager em) {
//        Providencia providencia = new Providencia();
//        providencia.setFechaCreacion(DEFAULT_FECHA_CREADO);
//        return providencia;
//    }
//
//    @Before
//    public void initTest() {
//        providencia = createEntity(em);
//    }
//
//    @Test
//    @Transactional
//    public void createProvidencia() throws Exception {
//        int databaseSizeBeforeCreate = providenciaRepository.findAll().size();
//
//        // Create the Providencia
//        ProvidenciaDTO providenciaDTO = providenciaMapper.toDto(providencia);
//        restProvidenciaMockMvc.perform(post("/api/providencias")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(providenciaDTO)))
//            .andExpect(status().isCreated());
//
//        // Validate the Providencia in the database
//        List<Providencia> providenciaList = providenciaRepository.findAll();
//        assertThat(providenciaList).hasSize(databaseSizeBeforeCreate + 1);
//        Providencia testProvidencia = providenciaList.get(providenciaList.size() - 1);
////        assertThat(testProvidencia.getDescripcion()).isEqualTo(DEFAULT_DESCRIPCION);
////        assertThat(testProvidencia.getFechaCreado()).isEqualTo(DEFAULT_FECHA_CREADO);
////        assertThat(testProvidencia.getFechaIngreso()).isEqualTo(DEFAULT_FECHA_INGRESO);
////        assertThat(testProvidencia.getEstado()).isEqualTo(DEFAULT_ESTADO);
//    }
//
//    @Test
//    @Transactional
//    public void createProvidenciaWithExistingId() throws Exception {
//        int databaseSizeBeforeCreate = providenciaRepository.findAll().size();
//
//        // Create the Providencia with an existing ID
//        providencia.setId(1L);
//        ProvidenciaDTO providenciaDTO = providenciaMapper.toDto(providencia);
//
//        // An entity with an existing ID cannot be created, so this API call must fail
//        restProvidenciaMockMvc.perform(post("/api/providencias")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(providenciaDTO)))
//            .andExpect(status().isBadRequest());
//
//        // Validate the Providencia in the database
//        List<Providencia> providenciaList = providenciaRepository.findAll();
//        assertThat(providenciaList).hasSize(databaseSizeBeforeCreate);
//    }
//
//    @Test
//    @Transactional
//    public void checkFechaIngresoIsRequired() throws Exception {
//        int databaseSizeBeforeTest = providenciaRepository.findAll().size();
//        // set the field null
////        providencia.setFechaIngreso(null);
//
//        // Create the Providencia, which fails.
//        ProvidenciaDTO providenciaDTO = providenciaMapper.toDto(providencia);
//
//        restProvidenciaMockMvc.perform(post("/api/providencias")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(providenciaDTO)))
//            .andExpect(status().isBadRequest());
//
//        List<Providencia> providenciaList = providenciaRepository.findAll();
//        assertThat(providenciaList).hasSize(databaseSizeBeforeTest);
//    }
//
//    @Test
//    @Transactional
//    public void checkEstadoIsRequired() throws Exception {
//        int databaseSizeBeforeTest = providenciaRepository.findAll().size();
//        // set the field null
////        providencia.setEstado(null);
//
//        // Create the Providencia, which fails.
//        ProvidenciaDTO providenciaDTO = providenciaMapper.toDto(providencia);
//
//        restProvidenciaMockMvc.perform(post("/api/providencias")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(providenciaDTO)))
//            .andExpect(status().isBadRequest());
//
//        List<Providencia> providenciaList = providenciaRepository.findAll();
//        assertThat(providenciaList).hasSize(databaseSizeBeforeTest);
//    }
//
//    @Test
//    @Transactional
//    public void getAllProvidencias() throws Exception {
//        // Initialize the database
//        providenciaRepository.saveAndFlush(providencia);
//
//        // Get all the providenciaList
//        restProvidenciaMockMvc.perform(get("/api/providencias?sort=id,desc"))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//            .andExpect(jsonPath("$.[*].id").value(hasItem(providencia.getId().intValue())))
//            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION.toString())))
//            .andExpect(jsonPath("$.[*].fechaCreado").value(hasItem(DEFAULT_FECHA_CREADO.toString())))
//            .andExpect(jsonPath("$.[*].fechaIngreso").value(hasItem(DEFAULT_FECHA_INGRESO.toString())))
//            .andExpect(jsonPath("$.[*].estado").value(hasItem(DEFAULT_ESTADO.toString())));
//    }
//
//    @Test
//    @Transactional
//    public void getProvidencia() throws Exception {
//        // Initialize the database
//        providenciaRepository.saveAndFlush(providencia);
//
//        // Get the providencia
//        restProvidenciaMockMvc.perform(get("/api/providencias/{id}", providencia.getId()))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//            .andExpect(jsonPath("$.id").value(providencia.getId().intValue()))
//            .andExpect(jsonPath("$.descripcion").value(DEFAULT_DESCRIPCION.toString()))
//            .andExpect(jsonPath("$.fechaCreado").value(DEFAULT_FECHA_CREADO.toString()))
//            .andExpect(jsonPath("$.fechaIngreso").value(DEFAULT_FECHA_INGRESO.toString()))
//            .andExpect(jsonPath("$.estado").value(DEFAULT_ESTADO.toString()));
//    }
//
//    @Test
//    @Transactional
//    public void getNonExistingProvidencia() throws Exception {
//        // Get the providencia
//        restProvidenciaMockMvc.perform(get("/api/providencias/{id}", Long.MAX_VALUE))
//            .andExpect(status().isNotFound());
//    }
//
//    @Test
//    @Transactional
//    public void updateProvidencia() throws Exception {
//        // Initialize the database
//        providenciaRepository.saveAndFlush(providencia);
//
//        int databaseSizeBeforeUpdate = providenciaRepository.findAll().size();
//
//        // Update the providencia
//        Providencia updatedProvidencia = providenciaRepository.findById(providencia.getId()).get();
//        // Disconnect from session so that the updates on updatedProvidencia are not directly saved in db
//        em.detach(updatedProvidencia);
////        updatedProvidencia
////            .descripcion(UPDATED_DESCRIPCION)
////            .fechaCreado(UPDATED_FECHA_CREADO)
////            .fechaIngreso(UPDATED_FECHA_INGRESO)
////            .estado(UPDATED_ESTADO);
//        ProvidenciaDTO providenciaDTO = providenciaMapper.toDto(updatedProvidencia);
//
//        restProvidenciaMockMvc.perform(put("/api/providencias")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(providenciaDTO)))
//            .andExpect(status().isOk());
//
//        // Validate the Providencia in the database
//        List<Providencia> providenciaList = providenciaRepository.findAll();
//        assertThat(providenciaList).hasSize(databaseSizeBeforeUpdate);
//        Providencia testProvidencia = providenciaList.get(providenciaList.size() - 1);
////        assertThat(testProvidencia.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
////        assertThat(testProvidencia.getFechaCreado()).isEqualTo(UPDATED_FECHA_CREADO);
////        assertThat(testProvidencia.getFechaIngreso()).isEqualTo(UPDATED_FECHA_INGRESO);
////        assertThat(testProvidencia.getEstado()).isEqualTo(UPDATED_ESTADO);
//    }
//
//    @Test
//    @Transactional
//    public void updateNonExistingProvidencia() throws Exception {
//        int databaseSizeBeforeUpdate = providenciaRepository.findAll().size();
//
//        // Create the Providencia
//        ProvidenciaDTO providenciaDTO = providenciaMapper.toDto(providencia);
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restProvidenciaMockMvc.perform(put("/api/providencias")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(providenciaDTO)))
//            .andExpect(status().isBadRequest());
//
//        // Validate the Providencia in the database
//        List<Providencia> providenciaList = providenciaRepository.findAll();
//        assertThat(providenciaList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    public void deleteProvidencia() throws Exception {
//        // Initialize the database
//        providenciaRepository.saveAndFlush(providencia);
//
//        int databaseSizeBeforeDelete = providenciaRepository.findAll().size();
//
//        // Get the providencia
//        restProvidenciaMockMvc.perform(delete("/api/providencias/{id}", providencia.getId())
//            .accept(TestUtil.APPLICATION_JSON_UTF8))
//            .andExpect(status().isOk());
//
//        // Validate the database is empty
//        List<Providencia> providenciaList = providenciaRepository.findAll();
//        assertThat(providenciaList).hasSize(databaseSizeBeforeDelete - 1);
//    }
//
//    @Test
//    @Transactional
//    public void equalsVerifier() throws Exception {
//        TestUtil.equalsVerifier(Providencia.class);
//        Providencia providencia1 = new Providencia();
//        providencia1.setId(1L);
//        Providencia providencia2 = new Providencia();
//        providencia2.setId(providencia1.getId());
//        assertThat(providencia1).isEqualTo(providencia2);
//        providencia2.setId(2L);
//        assertThat(providencia1).isNotEqualTo(providencia2);
//        providencia1.setId(null);
//        assertThat(providencia1).isNotEqualTo(providencia2);
//    }
//
//    @Test
//    @Transactional
//    public void dtoEqualsVerifier() throws Exception {
//        TestUtil.equalsVerifier(ProvidenciaDTO.class);
//        ProvidenciaDTO providenciaDTO1 = new ProvidenciaDTO();
//        providenciaDTO1.setId(1L);
//        ProvidenciaDTO providenciaDTO2 = new ProvidenciaDTO();
//        assertThat(providenciaDTO1).isNotEqualTo(providenciaDTO2);
//        providenciaDTO2.setId(providenciaDTO1.getId());
//        assertThat(providenciaDTO1).isEqualTo(providenciaDTO2);
//        providenciaDTO2.setId(2L);
//        assertThat(providenciaDTO1).isNotEqualTo(providenciaDTO2);
//        providenciaDTO1.setId(null);
//        assertThat(providenciaDTO1).isNotEqualTo(providenciaDTO2);
//    }
//
//    @Test
//    @Transactional
//    public void testEntityFromId() {
//        assertThat(providenciaMapper.fromId(42L).getId()).isEqualTo(42);
//        assertThat(providenciaMapper.fromId(null)).isNull();
//    }
//}
