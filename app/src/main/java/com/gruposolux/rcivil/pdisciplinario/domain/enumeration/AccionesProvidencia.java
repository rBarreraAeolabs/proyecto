package com.gruposolux.rcivil.pdisciplinario.domain.enumeration;

/**
 * The AccionProvidencia enumeration.
 */
public enum AccionesProvidencia {
    // REQUISITOS PARA MOVER EL FLUJO
    CREAR_PROVIDENCIA,
    DGD_ASIGNA_NUMERO_REFERENCIA,
    SUB_DIRECCION_RECIBE_PROVIDENCIA,
    SUB_DIRECCION_ASIGNA,
    SUB_DIRECCION_RECIBE,
    GESTOR_DOCUMENTAL_ASIGNO_NUMERO,


    UPD_REDACTA_RESOLUCION_Y_MEMO,
    ENVIAR_A_SUB_DIRECCION,
    SUB_DIRECCION_FIRMA_VISA,
    ENVIADA_A_DGD,
    // DGD_REGISTRA,
    ENVIADA_A_DN,

    DN_FIRMA_RESOLUCION,
    ENVIA_A_DGDP,

    DGDP_ASIGNA_NUMERO,
    ENVIADA_A_UPD,

    DGD_REGISTRA,
    UPD_ADJUNTO_NOTIFICACION_VISTA_FISCAL,
    ENVIAR_A_UPD,

    UPD_NOTIFICO_A_FISCAL,

    FISCAL_RECIBE_NOTIFICACION,
    FISCAL_ACEPTA,
    FISCAL_RECHAZA,

    FISCAL_ENVIA_MEMO,
    UPD_INICIA,
    FISCAL_COMIENZA_INVESTIGACION,
    FISCAL_ENVIA_MEMO_CIERRE_A_UPD,
    UPD_REGISTRA_CIERRE,


    FISCAL_NOTIFICA_A_INCULPADO,
    INCULPADO_ENVIA_MEMO,
    FISCAL_FORMULA_CARGOS,

    TERMINO_PROBATORIO,
    REMITE_EXPEDIENTE_FISCAL,
    ENVIA_A_DN,
    DN_RECIBE_SUMARIO_COMPLETO,

    SUB_DIRECCION_ASIGNA_ABOGADO,
    ABOGADO_ELABORA_INFORME,
    ENVIA_INFORME_A_SUB_DIRECCION,
    SUB_DIRECCION_ENVIA_A_DN_INFORME_JURIDICO,
    SUB_DIRECCION_CONFIRMA,
    FISCAL_ADJUNTA_MEMO,
    UPD_DIO_INICIO,
    FISCAL_TERMINO_INVESTIGACION,
    FISCAL_ADJUNTA_MEMO_CIERRE,

    FISCAL_NOTIFICO_A_INCULPADO,
    INCULPADO_ENVIO_MEMO,
    FISCAL_FORMULO_CARGOS,
    CONFIRMA_TERMINO_PROBATORIO,
    REMITE_EXPEDIENTE,
    DN_CONFIRMA_RECEPCION_SUMARIO_COMPLETO,
    SUB_DIRECCION_ASIGNO_ABOGADO,
    ADJUNTA_INFORME,
    SELECCIONO_SOBRECEDER,
    SELECCIONO_ABSORVE,
    SELECCIONO_REABRIR,
    SELECCIONO_SANCIONAR,

    FLUJO_ABSOLVER,
    FLUJO_SOBRESEER_ABSOLVER,
    FLUJO_SANCION,
    FLUJO_REABRIR,
    FLUJO_NO_APELA,
    FLUJO_APELA,

    CONTINUAR_FLUJO_APELA_SUSPENCION_MULTA,
    CONTINUAR_FLUJO_APELA_SENSURA_DESTITUCION,

    FLUJO_REGISTRA,
    FLUJO_TOMA_RAZON,
    FLUJO_REPRESENTA,
    PRORROGA,
    PRORROGA2,

    /**
    para investacion luego de prorroga
     */
    INVESTIGADO,
    FISCAL_NOTIFICA_A_UPD_CIERRE,
    INCULPADO_NO_ENVIA_MEMO,
    SELECCION_INFORME_ABOGADO,
    FORMULAR_CARGOS,
    NO_PROPONE,
    NO_REABRE,
    SI_DE_ACUERDO,
    ASIGNA_FOLIO,
    NOTIFICA,
    SELECCION_FISCAL,
    NOTIFICA_INCULPADO,
    SELECCIONO_EXAMEN_LEGALIDAD,
    SELECCIONO_ALCANCE ,
    SELECCIONO_ALCANCE_CON_RESOLUCION,
    SELECCIONO_ALCANCE_SIN_RESOLUCION,
    INFORMA_A_DGDP,
    NOTIFICO_A_REMUNERACION,
    NOTIFICAR_A_DGDP,
    NOTIFICA_DENUNCIANTE,
    SELECCIONO_RESOLUCION,
    FLUJO_DEVOLVER,
    FLUJO_DEVOLVER_PRORROGA1,
    FLUJO_DEVOLVER_PRORROGA2,
    EMITIR_PROVIDENCIA,
}

