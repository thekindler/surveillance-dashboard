package com.infosys.surveillance;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.infosys.surveillance");

        noClasses()
            .that()
                .resideInAnyPackage("com.infosys.surveillance.service..")
            .or()
                .resideInAnyPackage("com.infosys.surveillance.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..com.infosys.surveillance.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
