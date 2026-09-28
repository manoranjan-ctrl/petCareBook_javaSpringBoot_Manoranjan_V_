const API = "http://localhost:8080";

// ===============================
// PAGE NAVIGATION
// ===============================

function showSection(sectionId) {

    const sections = document.querySelectorAll(".section");

    sections.forEach(section => {
        section.classList.add("hidden");
    });

    document.getElementById(sectionId).classList.remove("hidden");

    if (sectionId === "dashboard") {
        loadDashboard();
    }

    if (sectionId === "owners") {
        loadOwners();
    }

    if (sectionId === "pets") {
        loadPets();
    }

    if (sectionId === "vaccines") {
        loadVaccines();
    }

    if (sectionId === "vaccinations") {
        loadVaccinations();
    }
}


// ===============================
// DASHBOARD
// ===============================

async function loadDashboard() {

    try {

        const ownersResponse = await fetch(`${API}/owners`);
        const owners = await ownersResponse.json();

        const petsResponse = await fetch(`${API}/pets`);
        const pets = await petsResponse.json();

        const vaccinesResponse = await fetch(`${API}/vaccineTypes`);
        const vaccines = await vaccinesResponse.json();

        document.getElementById("ownerCount").textContent = owners.length;
        document.getElementById("petCount").textContent = pets.length;
        document.getElementById("vaccineCount").textContent = vaccines.length;

        loadUpcomingVaccinations();

    } catch (error) {

        console.error("Dashboard error:", error);

    }
}


// ===============================
// UPCOMING VACCINATIONS
// ===============================

async function loadUpcomingVaccinations() {

    try {

        const response = await fetch(`${API}/upcomingVaccinations`);

        if (!response.ok) {
            throw new Error("Unable to load upcoming vaccinations");
        }

        const records = await response.json();

        const table = document.getElementById("upcomingTable");

        table.innerHTML = "";

        records.forEach(record => {

            const row = document.createElement("tr");

            row.innerHTML = `
                <td>${record.pet?.name || "Pet ID: " + record.pet?.petId}</td>
                <td>${record.vaccineType?.vaccineName || "Vaccine ID: " + record.vaccineType?.vaccineId}</td>
                <td>${record.nextDueDate}</td>
            `;

            table.appendChild(row);

        });

    } catch (error) {

        console.error("Upcoming vaccination error:", error);

    }
}


// ===============================
// OWNERS
// ===============================

async function loadOwners() {

    try {

        const response = await fetch(`${API}/owners`);

        if (!response.ok) {
            throw new Error("Unable to load owners");
        }

        const owners = await response.json();

        const table = document.getElementById("ownersTable");

        table.innerHTML = "";

        owners.forEach(owner => {

            const row = document.createElement("tr");

            row.innerHTML = `
                <td>${owner.ownerId}</td>
                <td>${owner.name}</td>
                <td>${owner.phone}</td>

                <td>
                    <button onclick="editOwner(${owner.ownerId}, '${owner.name}', '${owner.phone}')">
                        Edit
                    </button>

                    <button onclick="deleteOwner(${owner.ownerId})">
                        Delete
                    </button>
                </td>
            `;

            table.appendChild(row);

        });

    } catch (error) {

        console.error("Owner loading error:", error);

    }
}


// ADD OWNER

document.getElementById("ownerForm").addEventListener("submit", async function(event) {

    event.preventDefault();

    const owner = {

        name: document.getElementById("ownerName").value,

        phone: document.getElementById("ownerPhone").value

    };

    try {

        const response = await fetch(`${API}/addOwner`, {

            method: "POST",

            headers: {
                "Content-Type": "application/json"
            },

            body: JSON.stringify(owner)

        });

        if (!response.ok) {

            const error = await response.json();

            alert(error.error || "Unable to add owner");

            return;

        }

        alert("Owner added successfully!");

        document.getElementById("ownerForm").reset();

        loadOwners();

        loadDashboard();

    } catch (error) {

        alert("Unable to connect to backend.");

        console.error(error);

    }

});


// EDIT OWNER

async function editOwner(id, oldName, oldPhone) {

    const name = prompt("Enter owner name:", oldName);

    if (name === null) {
        return;
    }

    const phone = prompt("Enter phone number:", oldPhone);

    if (phone === null) {
        return;
    }

    const owner = {

        name: name,

        phone: phone

    };

    try {

        const response = await fetch(`${API}/owners/${id}`, {

            method: "PUT",

            headers: {
                "Content-Type": "application/json"
            },

            body: JSON.stringify(owner)

        });

        if (!response.ok) {

            const error = await response.json();

            alert(error.error || "Unable to update owner");

            return;

        }

        alert("Owner updated successfully!");

        loadOwners();

        loadDashboard();

    } catch (error) {

        alert("Unable to connect to backend.");

        console.error(error);

    }

}


// DELETE OWNER

async function deleteOwner(id) {

    if (!confirm("Are you sure you want to delete this owner?")) {
        return;
    }

    try {

        const response = await fetch(`${API}/owners/${id}`, {

            method: "DELETE"

        });

        if (!response.ok) {

            const error = await response.json();

            alert(error.error || "Unable to delete owner");

            return;

        }

        alert("Owner deleted successfully!");

        loadOwners();

        loadDashboard();

    } catch (error) {

        alert("Unable to connect to backend.");

        console.error(error);

    }

}


// ===============================
// PETS
// ===============================

async function loadPets() {

    try {

        const response = await fetch(`${API}/pets`);

        if (!response.ok) {
            throw new Error("Unable to load pets");
        }

        const pets = await response.json();

        const table = document.getElementById("petsTable");

        table.innerHTML = "";

        pets.forEach(pet => {

            const row = document.createElement("tr");

            row.innerHTML = `
                <td>${pet.petId}</td>
                <td>${pet.name}</td>
                <td>${pet.species}</td>
                <td>${pet.breed}</td>
                <td>${pet.dateOfBirth}</td>
                <td>${pet.owner?.ownerId || ""}</td>

                <td>

                    <button onclick="editPet(
                        ${pet.petId},
                        '${pet.name}',
                        '${pet.species}',
                        '${pet.breed}',
                        '${pet.dateOfBirth}',
                        ${pet.owner?.ownerId || 0}
                    )">
                        Edit
                    </button>

                    <button onclick="deletePet(${pet.petId})">
                        Delete
                    </button>

                </td>
            `;

            table.appendChild(row);

        });

    } catch (error) {

        console.error("Pet loading error:", error);

    }
}


// ADD PET

document.getElementById("petForm").addEventListener("submit", async function(event) {

    event.preventDefault();

    const pet = {

        name: document.getElementById("petName").value,

        species: document.getElementById("petSpecies").value,

        breed: document.getElementById("petBreed").value,

        dateOfBirth: document.getElementById("petDob").value,

        owner: {

            ownerId: Number(
                document.getElementById("petOwnerId").value
            )

        }

    };

    try {

        const response = await fetch(`${API}/addPet`, {

            method: "POST",

            headers: {

                "Content-Type": "application/json"

            },

            body: JSON.stringify(pet)

        });

        if (!response.ok) {

            const error = await response.json();

            alert(error.error || "Unable to add pet");

            return;

        }

        alert("Pet added successfully!");

        document.getElementById("petForm").reset();

        loadPets();

        loadDashboard();

    } catch (error) {

        alert("Unable to connect to backend.");

        console.error(error);

    }

});


// EDIT PET

async function editPet(
    id,
    oldName,
    oldSpecies,
    oldBreed,
    oldDob,
    oldOwnerId
) {

    const name = prompt("Pet name:", oldName);

    if (name === null) return;

    const species = prompt("Species:", oldSpecies);

    if (species === null) return;

    const breed = prompt("Breed:", oldBreed);

    if (breed === null) return;

    const dob = prompt("Date of birth (YYYY-MM-DD):", oldDob);

    if (dob === null) return;

    const ownerId = prompt("Owner ID:", oldOwnerId);

    if (ownerId === null) return;


    const pet = {

        name: name,

        species: species,

        breed: breed,

        dateOfBirth: dob,

        owner: {

            ownerId: Number(ownerId)

        }

    };


    try {

        const response = await fetch(`${API}/pets/${id}`, {

            method: "PUT",

            headers: {

                "Content-Type": "application/json"

            },

            body: JSON.stringify(pet)

        });

        if (!response.ok) {

            const error = await response.json();

            alert(error.error || "Unable to update pet");

            return;

        }

        alert("Pet updated successfully!");

        loadPets();

        loadDashboard();

    } catch (error) {

        alert("Unable to connect to backend.");

        console.error(error);

    }

}


// DELETE PET

async function deletePet(id) {

    if (!confirm("Are you sure you want to delete this pet?")) {
        return;
    }

    try {

        const response = await fetch(`${API}/pets/${id}`, {

            method: "DELETE"

        });

        if (!response.ok) {

            const error = await response.json();

            alert(error.error || "Unable to delete pet");

            return;

        }

        alert("Pet deleted successfully!");

        loadPets();

        loadDashboard();

    } catch (error) {

        alert("Unable to connect to backend.");

        console.error(error);

    }

}


// ===============================
// VACCINES
// ===============================

async function loadVaccines() {

    try {

        const response = await fetch(`${API}/vaccineTypes`);

        if (!response.ok) {
            throw new Error("Unable to load vaccines");
        }

        const vaccines = await response.json();

        const table = document.getElementById("vaccinesTable");

        table.innerHTML = "";

        vaccines.forEach(vaccine => {

            const row = document.createElement("tr");

            row.innerHTML = `

                <td>${vaccine.vaccineId}</td>

                <td>${vaccine.vaccineName}</td>

                <td>${vaccine.intervalDays}</td>

                <td>

                    <button onclick="editVaccine(
                        ${vaccine.vaccineId},
                        '${vaccine.vaccineName}',
                        ${vaccine.intervalDays}
                    )">
                        Edit
                    </button>

                    <button onclick="deleteVaccine(${vaccine.vaccineId})">
                        Delete
                    </button>

                </td>
            `;

            table.appendChild(row);

        });

    } catch (error) {

        console.error("Vaccine loading error:", error);

    }
}


// ADD VACCINE

document.getElementById("vaccineForm").addEventListener("submit", async function(event) {

    event.preventDefault();

    const vaccine = {

        vaccineName: document.getElementById("vaccineName").value,

        intervalDays: Number(
            document.getElementById("intervalDays").value
        )

    };

    try {

        const response = await fetch(`${API}/addVaccineType`, {

            method: "POST",

            headers: {

                "Content-Type": "application/json"

            },

            body: JSON.stringify(vaccine)

        });

        if (!response.ok) {

            const error = await response.json();

            alert(error.error || "Unable to add vaccine");

            return;

        }

        alert("Vaccine added successfully!");

        document.getElementById("vaccineForm").reset();

        loadVaccines();

        loadDashboard();

    } catch (error) {

        alert("Unable to connect to backend.");

        console.error(error);

    }

});


// EDIT VACCINE

async function editVaccine(id, oldName, oldInterval) {

    const name = prompt("Vaccine name:", oldName);

    if (name === null) return;

    const interval = prompt(
        "Interval days:",
        oldInterval
    );

    if (interval === null) return;


    const vaccine = {

        vaccineName: name,

        intervalDays: Number(interval)

    };


    try {

        const response = await fetch(`${API}/vaccineTypes/${id}`, {

            method: "PUT",

            headers: {

                "Content-Type": "application/json"

            },

            body: JSON.stringify(vaccine)

        });

        if (!response.ok) {

            const error = await response.json();

            alert(error.error || "Unable to update vaccine");

            return;

        }

        alert("Vaccine updated successfully!");

        loadVaccines();

        loadDashboard();

    } catch (error) {

        alert("Unable to connect to backend.");

        console.error(error);

    }

}


// DELETE VACCINE

async function deleteVaccine(id) {

    if (!confirm("Are you sure you want to delete this vaccine?")) {
        return;
    }

    try {

        const response = await fetch(`${API}/vaccineTypes/${id}`, {

            method: "DELETE"

        });

        if (!response.ok) {

            const error = await response.json();

            alert(error.error || "Unable to delete vaccine");

            return;

        }

        alert("Vaccine deleted successfully!");

        loadVaccines();

        loadDashboard();

    } catch (error) {

        alert("Unable to connect to backend.");

        console.error(error);

    }

}


// ===============================
// VACCINATION RECORDS
// ===============================

async function loadVaccinations() {

    try {

        const response = await fetch(`${API}/vaccinationRecords`);

        if (!response.ok) {
            throw new Error("Unable to load vaccination records");
        }

        const records = await response.json();

        const table = document.getElementById("vaccinationsTable");

        table.innerHTML = "";

        records.forEach(record => {

            const row = document.createElement("tr");

            row.innerHTML = `

                <td>${record.vaccinationRecordId}</td>

                <td>${record.date}</td>

                <td>${record.nextDueDate}</td>

                <td>${record.pet?.petId || ""}</td>

                <td>${record.vaccineType?.vaccineId || ""}</td>

                <td>

                    <button onclick="editVaccination(
                        ${record.vaccinationRecordId},
                        '${record.date}',
                        ${record.pet?.petId || 0},
                        ${record.vaccineType?.vaccineId || 0}
                    )">
                        Edit
                    </button>

                    <button onclick="deleteVaccination(
                        ${record.vaccinationRecordId}
                    )">
                        Delete
                    </button>

                </td>

            `;

            table.appendChild(row);

        });

    } catch (error) {

        console.error("Vaccination loading error:", error);

    }

}


// ADD VACCINATION

document.getElementById("vaccinationForm").addEventListener("submit", async function(event) {

    event.preventDefault();

    const vaccination = {

        date: document.getElementById("vaccinationDate").value,

        pet: {

            petId: Number(
                document.getElementById("vaccinationPetId").value
            )

        },

        vaccineType: {

            vaccineId: Number(
                document.getElementById("vaccinationVaccineId").value
            )

        }

    };


    try {

        const response = await fetch(
            `${API}/addVaccinationRecord`,
            {

                method: "POST",

                headers: {

                    "Content-Type": "application/json"

                },

                body: JSON.stringify(vaccination)

            }
        );


        if (!response.ok) {

            const error = await response.json();

            alert(
                error.error ||
                "Unable to add vaccination record"
            );

            return;

        }


        alert("Vaccination record added successfully!");

        document.getElementById("vaccinationForm").reset();

        loadVaccinations();

        loadUpcomingVaccinations();

    } catch (error) {

        alert("Unable to connect to backend.");

        console.error(error);

    }

});


// EDIT VACCINATION

async function editVaccination(
    id,
    oldDate,
    oldPetId,
    oldVaccineId
) {

    const date = prompt(
        "Vaccination date (YYYY-MM-DD):",
        oldDate
    );

    if (date === null) return;


    const petId = prompt(
        "Pet ID:",
        oldPetId
    );

    if (petId === null) return;


    const vaccineId = prompt(
        "Vaccine ID:",
        oldVaccineId
    );

    if (vaccineId === null) return;


    const vaccination = {

        date: date,

        pet: {

            petId: Number(petId)

        },

        vaccineType: {

            vaccineId: Number(vaccineId)

        }

    };


    try {

        const response = await fetch(
            `${API}/vaccinationRecords/${id}`,
            {

                method: "PUT",

                headers: {

                    "Content-Type": "application/json"

                },

                body: JSON.stringify(vaccination)

            }
        );


        if (!response.ok) {

            const error = await response.json();

            alert(
                error.error ||
                "Unable to update vaccination"
            );

            return;

        }


        alert("Vaccination record updated successfully!");

        loadVaccinations();

        loadUpcomingVaccinations();

    } catch (error) {

        alert("Unable to connect to backend.");

        console.error(error);

    }

}


// DELETE VACCINATION

async function deleteVaccination(id) {

    if (
        !confirm(
            "Are you sure you want to delete this vaccination record?"
        )
    ) {
        return;
    }


    try {

        const response = await fetch(
            `${API}/vaccinationRecords/${id}`,
            {

                method: "DELETE"

            }
        );


        if (!response.ok) {

            const error = await response.json();

            alert(
                error.error ||
                "Unable to delete vaccination record"
            );

            return;

        }


        alert("Vaccination record deleted successfully!");

        loadVaccinations();

        loadUpcomingVaccinations();

    } catch (error) {

        alert("Unable to connect to backend.");

        console.error(error);

    }

}


// ===============================
// START APPLICATION
// ===============================

window.onload = function() {

    loadDashboard();

};