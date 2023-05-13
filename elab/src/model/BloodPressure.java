package model;

/**
 * Enumeration representing different levels of blood pressure.
 */
public enum BloodPressure {
    
    /**
     * Represents the ideal blood pressure level.
     */
    OPTIMAL,
    
    /**
     * Represents the typical blood pressure level.
     */
    NORMAL,
    
    /**
     * Represents higher than normal blood pressure level.
     */
    HIGH,
    
    /**
     * Represents borderline high blood pressure level.
     */
    GRADE_1_BORDERLINE,
    
    /**
     * Represents mild high blood pressure level.
     */
    GRADE_1_MILD,
    
    /**
     * Represents moderate high blood pressure level.
     */
    GRADE_2_MODERATE,
    
    /**
     * Represents severe high blood pressure level.
     */
    GRADE_3_SEVERE,
    
    /**
     * Represents borderline high systolic blood pressure level.
     */
    ISOLATED_SYSTOLIC_BORDERLINE,
    
    /**
     * Represents high systolic blood pressure level.
     */
    ISOLATED_SYSTOLIC
}