package com.project.Svalbard.Model.db;

import org.hibernate.annotations.Type;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.UUID;

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
    //region Dataset Attributes and getters and setters Mapped to the columns
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UUID getFid() {
        return fid;
    }

    public void setFid(UUID fid) {
        this.fid = fid;
    }

    public int getNumericAttributes() {
        return NumericAttributes;
    }

    public void setNumericAttributes(int numericAttributes) {
        NumericAttributes = numericAttributes;
    }

    public int getInstances() {
        return Instances;
    }

    public void setInstances(int instances) {
        Instances = instances;
    }

    public int getFeatures() {
        return Features;
    }

    public void setFeatures(int features) {
        Features = features;
    }

    public double getDimensionality() {
        return Dimensionality;
    }

    public void setDimensionality(double dimensionality) {
        Dimensionality = dimensionality;
    }

    public double getPercentageOfMissingValues() {
        return PercentageOfMissingValues;
    }

    public void setPercentageOfMissingValues(double percentageOfMissingValues) {
        PercentageOfMissingValues = percentageOfMissingValues;
    }

    public double getPercentageofInstancesWithMissingValues() {
        return PercentageofInstancesWithMissingValues;
    }

    public void setPercentageofInstancesWithMissingValues(double percentageofInstancesWithMissingValues) {
        PercentageofInstancesWithMissingValues = percentageofInstancesWithMissingValues;
    }

    public double getPercentageBinaryFeatures() {
        return PercentageBinaryFeatures;
    }

    public void setPercentageBinaryFeatures(double percentageBinaryFeatures) {
        PercentageBinaryFeatures = percentageBinaryFeatures;
    }

    public double getPercentageFeaturesWithMoreThan10Uniques() {
        return PercentageFeaturesWithMoreThan10Uniques;
    }

    public void setPercentageFeaturesWithMoreThan10Uniques(double percentageFeaturesWithMoreThan10Uniques) {
        PercentageFeaturesWithMoreThan10Uniques = percentageFeaturesWithMoreThan10Uniques;
    }

    public int getMaxCardinalityNumericFeatures() {
        return MaxCardinalityNumericFeatures;
    }

    public void setMaxCardinalityNumericFeatures(int maxCardinalityNumericFeatures) {
        MaxCardinalityNumericFeatures = maxCardinalityNumericFeatures;
    }

    public int getMinCardinalityNumericFeatures() {
        return MinCardinalityNumericFeatures;
    }

    public void setMinCardinalityNumericFeatures(int minCardinalityNumericFeatures) {
        MinCardinalityNumericFeatures = minCardinalityNumericFeatures;
    }

    public int getMaxCardinalityNominalFeatures() {
        return MaxCardinalityNominalFeatures;
    }

    public void setMaxCardinalityNominalFeatures(int maxCardinalityNominalFeatures) {
        MaxCardinalityNominalFeatures = maxCardinalityNominalFeatures;
    }

    public int getMinCardinalityNominalFeatures() {
        return MinCardinalityNominalFeatures;
    }

    public void setMinCardinalityNominalFeatures(int minCardinalityNominalFeatures) {
        MinCardinalityNominalFeatures = minCardinalityNominalFeatures;
    }

    public double getPercentageNumericFeatures() {
        return PercentageNumericFeatures;
    }

    public void setPercentageNumericFeatures(double percentageNumericFeatures) {
        PercentageNumericFeatures = percentageNumericFeatures;
    }

    public double getMinMean() {
        return MinMean;
    }

    public void setMinMean(double minMean) {
        MinMean = minMean;
    }

    public double getMaxMean() {
        return MaxMean;
    }

    public void setMaxMean(double maxMean) {
        MaxMean = maxMean;
    }

    public double getMeanMean() {
        return MeanMean;
    }

    public void setMeanMean(double meanMean) {
        MeanMean = meanMean;
    }

    public double getMinStd() {
        return MinStd;
    }

    public void setMinStd(double minStd) {
        MinStd = minStd;
    }

    public double getMaxStd() {
        return MaxStd;
    }

    public void setMaxStd(double maxStd) {
        MaxStd = maxStd;
    }

    public double getMeanStd() {
        return MeanStd;
    }

    public void setMeanStd(double meanStd) {
        MeanStd = meanStd;
    }

    public double getQ1Mean() {
        return Q1Mean;
    }

    public void setQ1Mean(double q1Mean) {
        Q1Mean = q1Mean;
    }

    public double getQ2Mean() {
        return Q2Mean;
    }

    public void setQ2Mean(double q2Mean) {
        Q2Mean = q2Mean;
    }

    public double getQ3Mean() {
        return Q3Mean;
    }

    public void setQ3Mean(double q3Mean) {
        Q3Mean = q3Mean;
    }

    public double getQ1Std() {
        return Q1Std;
    }

    public void setQ1Std(double q1Std) {
        Q1Std = q1Std;
    }

    public double getQ2Std() {
        return Q2Std;
    }

    public void setQ2Std(double q2Std) {
        Q2Std = q2Std;
    }

    public double getQ3Std() {
        return Q3Std;
    }

    public void setQ3Std(double q3Std) {
        Q3Std = q3Std;
    }

    public int getNumberOfClasses() {
        return NumberOfClasses;
    }

    public void setNumberOfClasses(int numberOfClasses) {
        NumberOfClasses = numberOfClasses;
    }

    public double getMajorityClassPercentage() {
        return MajorityClassPercentage;
    }

    public void setMajorityClassPercentage(double majorityClassPercentage) {
        MajorityClassPercentage = majorityClassPercentage;
    }

    public double getMinorityClassPercentage() {
        return MinorityClassPercentage;
    }

    public void setMinorityClassPercentage(double minorityClassPercentage) {
        MinorityClassPercentage = minorityClassPercentage;
    }

    public double getMaximumCrossCorrelation() {
        return MaximumCrossCorrelation;
    }

    public void setMaximumCrossCorrelation(double maximumCrossCorrelation) {
        MaximumCrossCorrelation = maximumCrossCorrelation;
    }

    public double getMinimumCrossCorrelation() {
        return MinimumCrossCorrelation;
    }

    public void setMinimumCrossCorrelation(double minimumCrossCorrelation) {
        MinimumCrossCorrelation = minimumCrossCorrelation;
    }

    public double getMinSkew() {
        return MinSkew;
    }

    public void setMinSkew(double minSkew) {
        MinSkew = minSkew;
    }

    public double getMaxSkew() {
        return MaxSkew;
    }

    public void setMaxSkew(double maxSkew) {
        MaxSkew = maxSkew;
    }

    public double getMeanSkew() {
        return MeanSkew;
    }

    public void setMeanSkew(double meanSkew) {
        MeanSkew = meanSkew;
    }

    public double getQ1Skew() {
        return Q1Skew;
    }

    public void setQ1Skew(double q1Skew) {
        Q1Skew = q1Skew;
    }

    public double getQ2Skew() {
        return Q2Skew;
    }

    public void setQ2Skew(double q2Skew) {
        Q2Skew = q2Skew;
    }

    public double getQ3Skew() {
        return Q3Skew;
    }

    public void setQ3Skew(double q3Skew) {
        Q3Skew = q3Skew;
    }

    public double getMinKutosis() {
        return MinKutosis;
    }

    public void setMinKutosis(double minKutosis) {
        MinKutosis = minKutosis;
    }

    public double getMaxKutosis() {
        return MaxKutosis;
    }

    public void setMaxKutosis(double maxKutosis) {
        MaxKutosis = maxKutosis;
    }

    public double getMeanKutosis() {
        return MeanKutosis;
    }

    public void setMeanKutosis(double meanKutosis) {
        MeanKutosis = meanKutosis;
    }

    public double getQ1Kutosis() {
        return Q1Kutosis;
    }

    public void setQ1Kutosis(double q1Kutosis) {
        Q1Kutosis = q1Kutosis;
    }

    public double getQ2Kutosis() {
        return Q2Kutosis;
    }

    public void setQ2Kutosis(double q2Kutosis) {
        Q2Kutosis = q2Kutosis;
    }

    public double getQ3Kutosis() {
        return Q3Kutosis;
    }

    public void setQ3Kutosis(double q3Kutosis) {
        Q3Kutosis = q3Kutosis;
    }

    public double getMaxMutualInformation() {
        return MaxMutualInformation;
    }

    public void setMaxMutualInformation(double maxMutualInformation) {
        MaxMutualInformation = maxMutualInformation;
    }

    public double getMinMutualInformation() {
        return MinMutualInformation;
    }

    public void setMinMutualInformation(double minMutualInformation) {
        MinMutualInformation = minMutualInformation;
    }

    public double getQ1MutualInformation() {
        return Q1MutualInformation;
    }

    public void setQ1MutualInformation(double q1MutualInformation) {
        Q1MutualInformation = q1MutualInformation;
    }

    public double getQ2MutualInformation() {
        return Q2MutualInformation;
    }

    public void setQ2MutualInformation(double q2MutualInformation) {
        Q2MutualInformation = q2MutualInformation;
    }

    public double getQ3MutualInformation() {
        return Q3MutualInformation;
    }

    public void setQ3MutualInformation(double q3MutualInformation) {
        Q3MutualInformation = q3MutualInformation;
    }

    public double getMeanSignalToNoiseRatio() {
        return MeanSignalToNoiseRatio;
    }

    public void setMeanSignalToNoiseRatio(double meanSignalToNoiseRatio) {
        MeanSignalToNoiseRatio = meanSignalToNoiseRatio;
    }
    //endregion


}
