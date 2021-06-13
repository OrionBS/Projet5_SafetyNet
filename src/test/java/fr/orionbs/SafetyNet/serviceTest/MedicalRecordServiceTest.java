package fr.orionbs.SafetyNet.serviceTest;

import fr.orionbs.SafetyNet.model.MedicalRecord;
import fr.orionbs.SafetyNet.repository.MedicalRecordRepository;
import fr.orionbs.SafetyNet.service.impl.MedicalRecordServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MedicalRecordServiceTest {

    @InjectMocks
    MedicalRecordServiceImpl medicalRecordService;

    @Mock
    private MedicalRecordRepository medicalRecordRepository;

    @Test
    public void testAddMedicalRecord() {
        //GIVEN
        MedicalRecord medicalRecord = new MedicalRecord(null,"test","Test","03/02/2000",new ArrayList<>(),new ArrayList<>());

        //WHEN
        when(medicalRecordRepository.save(any(MedicalRecord.class))).thenReturn(medicalRecord);

        medicalRecordService.addMedicalRecord(medicalRecord);

        //THEN
        verify(medicalRecordRepository, Mockito.times(1)).save(any(MedicalRecord.class));
    }

    @Test
    public void testUpdateMedicalRecord() {
        //GIVEN
        MedicalRecord medicalRecord = MedicalRecord.builder().firstName("test").lastName("test").birthdate("06/12/2012").build();
        when(medicalRecordRepository.findByFirstNameAndLastName(anyString(),anyString())).thenReturn(medicalRecord);
        when(medicalRecordRepository.save(any(MedicalRecord.class))).thenReturn(medicalRecord);

        //WHEN
        medicalRecordService.updateMedicalRecord(medicalRecord.getFirstName(),medicalRecord.getLastName(),medicalRecord);

        //THEN
        verify(medicalRecordRepository,Mockito.times(1)).findByFirstNameAndLastName(anyString(),anyString());
        verify(medicalRecordRepository,Mockito.times(1)).save(any(MedicalRecord.class));
    }

    @Test
    public void testSave() {
        //GIVEN
        List<MedicalRecord> medicalRecordList = Arrays.asList(MedicalRecord.builder().firstName("test").lastName("test").birthdate("06/12/2012").build());
        when(medicalRecordRepository.saveAll(anyList())).thenReturn(medicalRecordList);

        //WHEN
        medicalRecordService.save(anyList());

        //THEN
        verify(medicalRecordRepository,Mockito.times(1)).saveAll(anyList());
    }

    @Test
    public void testFindAll() {
        //GIVEN
        List<MedicalRecord> medicalRecordList = Arrays.asList(MedicalRecord.builder().firstName("test").lastName("test").birthdate("06/12/2012").build());
        when(medicalRecordRepository.findAll()).thenReturn(medicalRecordList);

        //WHEN
        medicalRecordService.findAll();

        //THEN
        verify(medicalRecordRepository,Mockito.times(1)).findAll();
    }

    @Test
    public void testGetMedicalRecord() {
        //GIVEN
        MedicalRecord medicalRecord = MedicalRecord.builder().firstName("test").lastName("test").birthdate("06/12/2012").build();
        when(medicalRecordRepository.findByFirstNameAndLastName(anyString(),anyString())).thenReturn(medicalRecord);

        //WHEN
        medicalRecordService.getMedicalRecord(anyString(),anyString());

        //THEN
        verify(medicalRecordRepository,Mockito.times(1)).findByFirstNameAndLastName(anyString(),anyString());

    }
}
