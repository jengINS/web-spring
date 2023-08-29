

 src='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js'

 $(document).ready(function () {
    var down = false;
    $('#bell').click(function (e) {

        var color = $(this).text();
        if (down) {

            $('#box').css('height', '0px');
            $('#box').css('opacity', '0');
            down = false;
        } else {

            $('#box').css('height', 'auto');
            $('#box').css('opacity', '1');
            down = true;

        }

    });

});


const  user_name = document.currentScript.getAttribute('data-array');
var dataArrayQ=null;
fetch(`/get-query?name=${user_name}`)
    .then(response => response.json()) // Parse the response as JSON
    .then(dataArray => {
        dataArrayQ=dataArray;
        genNoti();
        // Process the dataArray as needed
    })
    .catch(error => {
        console.error("Error fetching Query:", error);
    });
    


function genNoti() {
    
var dataArray = dataArrayQ;

    var dataArraycount =  dataArrayQ.length;
    var count = dataArraycount;
    var alertC = document.getElementById("alertCount");
    if(count!=0)
    alertC.innerText = count;
    var container = document.getElementById("container");
    var countN = 1;
    dataArray.forEach(function (row) {

        var notificationDiv = document.createElement("div");
        notificationDiv.className = "notifications-item";
        notificationDiv.id = "noti" + countN;
        var imgD = document.createElement("img");
        imgD.src = "/image/alert-ic.png";
        notificationDiv.appendChild(imgD);
        var strDiv = document.createElement("div");
        strDiv.className = "text";
        var h4Div = document.createElement("h4");
        h4Div.innerText = row[2];
        var pDiv = document.createElement("p");
        pDiv.innerText = row[3];
        strDiv.appendChild(h4Div);
        strDiv.appendChild(pDiv);
        notificationDiv.appendChild(strDiv);
        notificationDiv.addEventListener("click", function () {
            window.location.href = "#";
        });
        container.appendChild(notificationDiv);
        countN++;
    });
   
}