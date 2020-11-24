package com.project.Svalbard.Model.db;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Type;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.UUID;

@Data
@Component
@Entity
@Table(name = "dataset")
public class Dataset {

    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Id
    @Column(name = "fid")
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID fid;

    @Column(name = "name")
    private String name;
    //region Dataset Attributes Mapped to the columns
    @Column(name = "NumericAttributes")
    private int NumericAttributes;
    @Column(name = "Instances")
    private int Instances;
    @Column(name = "Features")
    private int Features;
    @Column(name = "Dimensionality")
    private double Dimensionality;
    @Column(name = "PercentageOfMissingValues")
    private double PercentageOfMissingValues;
    @Column(name = "PercentageofInstancesWithMissingValues")
    private double PercentageofInstancesWithMissingValues;
    @Column(name = "PercentageBinaryFeatures")
    private double PercentageBinaryFeatures;
    @Column(name = "PercentageFeaturesWithMoreThan10Uniques")
    private double PercentageFeaturesWithMoreThan10Uniques;
    @Column(name = "MaxCardinalityNumericFeatures")
    private int MaxCardinalityNumericFeatures;
    @Column(name = "MinCardinalityNumericFeatures")
    private int MinCardinalityNumericFeatures;
    @Column(name = "MaxCardinalityNominalFeatures")
    private int MaxCardinalityNominalFeatures;
    @Column(name = "MinCardinalityNominalFeatures")
    private int MinCardinalityNominalFeatures;
    @Column(name = "PercentageNumericFeatures ")
    private double PercentageNumericFeatures;
    @Column(name = "MinMean ")
    private double MinMean;
    @Column(name = "MaxMean ")
    private double MaxMean;
    @Column(name = "MeanMean ")
    private double MeanMean;
    @Column(name = "MinStd ")
    private double MinStd;
    @Column(name = "MaxStd ")
    private double MaxStd;
    @Column(name = "MeanStd ")
    private double MeanStd;
    @Column(name = "Q1Mean ")
    private double Q1Mean;
    @Column(name = "Q2Mean ")
    private double Q2Mean;
    @Column(name = "Q3Mean ")
    private double Q3Mean;
    @Column(name = "Q1Std ")
    private double Q1Std;
    @Column(name = "Q2Std ")
    private double Q2Std;
    @Column(name = "Q3Std ")
    private double Q3Std;
    @Column(name = "NumberOfClasses")
    private int NumberOfClasses;
    @Column(name = "MajorityClassPercentage")
    private double MajorityClassPercentage;
    @Column(name = "MinorityClassPercentage ")
    private double MinorityClassPercentage;
    @Column(name = "MaximumCrossCorrelation ")
    private double MaximumCrossCorrelation;
    @Column(name = "MinimumCrossCorrelation ")
    private double MinimumCrossCorrelation;
    @Column(name = "MinSkew ")
    private double MinSkew;
    @Column(name = "MaxSkew ")
    private double MaxSkew;
    @Column(name = "MeanSkew ")
    private double MeanSkew;
    @Column(name = "Q1Skew ")
    private double Q1Skew;
    @Column(name = "Q2Skew ")
    private double Q2Skew;
    @Column(name = "Q3Skew ")
    private double Q3Skew;
    @Column(name = "MinKutosis ")
    private double MinKutosis;
    @Column(name = "MaxKutosis ")
    private double MaxKutosis;
    @Column(name = "MeanKutosis ")
    private double MeanKutosis;
    @Column(name = "Q1Kutosis ")
    private double Q1Kutosis;
    @Column(name = "Q2Kutosis ")
    private double Q2Kutosis;
    @Column(name = "Q3Kutosis ")
    private double Q3Kutosis;
    @Column(name = "MaxMutualInformation ")
    private double MaxMutualInformation;
    @Column(name = "MinMutualInformation ")
    private double MinMutualInformation;
    @Column(name = "Q1MutualInformation ")
    private double Q1MutualInformation;
    @Column(name = "Q2MutualInformation ")
    private double Q2MutualInformation;
    @Column(name = "Q3MutualInformation ")
    private double Q3MutualInformation;
    @Column(name = "MeanSignalToNoiseRatio ")
    private double MeanSignalToNoiseRatio;

    public Dataset() {
    }
    //endregion


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("fid", fid)
                .append("name", name)
                .append("Instances", Instances)
                .append("Features", Features)
                .toString();
    }
}
