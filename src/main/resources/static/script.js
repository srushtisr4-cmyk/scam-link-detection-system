async function scanUrl() {

    const url = document.getElementById("urlInput").value;
    const resultDiv = document.getElementById("result");

    // Empty input check
    if (url.trim() === "") {

        resultDiv.innerHTML =
            "<span style='color:orange'>Please enter a URL</span>";

        return;
    }

    // Loading message
    resultDiv.innerHTML =
        "<span style='color:cyan'>🔍 Scanning URL...</span>";

    try {

        // Correct Backend URL
        const response = await fetch(
            "https://scam-link-detection-system-backend.onrender.com/api/scan",
            {
                method: "POST",

                headers: {
                    "Content-Type": "application/json"
                },

                body: JSON.stringify({
                    url: url
                })
            }
        );

        // Convert response to JSON
        const data = await response.json();

        // Scam Result
        if (data.status === "Scam") {

            resultDiv.innerHTML = `

                <div style="
                    background:#220000;
                    padding:20px;
                    border-radius:15px;
                    margin-top:20px;
                ">

                    <h2 style="color:red;">
                        ⚠ Scam Link Detected
                    </h2>

                    <p>
                        <strong>URL:</strong> ${data.url}
                    </p>

                    <p style="color:red;">
                        <strong>Status:</strong> ${data.status}
                    </p>

                    <p style="color:orange;">
                        <strong>Risk Level:</strong> ${data.risk}
                    </p>

                    <p>
                        <strong>Threat Score:</strong> ${data.score}%
                    </p>

                    <div class="progress-bar">

                        <div class="progress"
                             style="width:${data.score}%">

                             ${data.score}%

                        </div>

                    </div>

                </div>
            `;
        }

        // Safe Result
        else {

            resultDiv.innerHTML = `

                <div style="
                    background:#002200;
                    padding:20px;
                    border-radius:15px;
                    margin-top:20px;
                ">

                    <h2 style="color:lightgreen;">
                        ✔ Safe Link
                    </h2>

                    <p>
                        <strong>URL:</strong> ${data.url}
                    </p>

                    <p style="color:lightgreen;">
                        <strong>Status:</strong> ${data.status}
                    </p>

                    <p style="color:cyan;">
                        <strong>Risk Level:</strong> ${data.risk}
                    </p>

                    <p>
                        <strong>Threat Score:</strong> ${data.score}%
                    </p>

                    <div class="progress-bar">

                        <div class="progress"
                             style="
                             width:${data.score}%;
                             background:green;">

                             ${data.score}%

                        </div>

                    </div>

                </div>
            `;
        }

    }

    catch (error) {

        resultDiv.innerHTML = `
            <span style="color:red;">
                Server Error. Backend may not be running.
            </span>
        `;

        console.error(error);
    }
}