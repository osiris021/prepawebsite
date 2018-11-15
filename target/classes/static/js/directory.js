var $pagination = $('#pagination'),
    totalRecords = 0,
    records = [],
    displayRecords = [],
    recPerPage = 8,
    page = 1,
    totalPages = 2;

$(function () {

    var result = '';
    var startIndex;
    var stopIndex;
    var xhr = new XMLHttpRequest();
    xhr.open('get', '/directory/getList', true);
    xhr.addEventListener('readystatechange', function () {
        if (xhr.readyState === 4) {
            result = xhr.responseText;
            startIndex = result.search("<span>");
            stopIndex = result.search("</span>");
            result = result.slice(startIndex + 6, stopIndex);
            result = replaceAll(result, "&quot;", "\"");
            records = JSON.parse(result);
            totalRecords = records.length;
            totalPages = Math.ceil(totalRecords / recPerPage);
            apply_pagination();
        }
    });
    xhr.send('');
});


function generate_table() {
    var tr;
    $('#emp_body').html('');
    for (var i = 0; i < displayRecords.length; i++) {
        tr = $('<tr/>');
        tr.append("<th><a href=\"/directory/" + displayRecords[i].student_id + "/details\" class=\"list-group-item nameStudent\">" + displayRecords[i].student_name + " " + displayRecords[i].student_firstname + "</a>");
        tr.append("<th class='promotionStudent'>" + displayRecords[i].student_promotion + "</th>");
        tr.append("<th class='schoolStudent'>" + displayRecords[i].student_school + "</th>");
        tr.append("<th class='yearStudent'>" + displayRecords[i].student_year + "</th>");
        tr.append("<th class='studentActualJob'>" + displayRecords[i].student_actual_job + "</th>");
        tr.append("<th class='Localisation'>" + displayRecords[i].student_city + ", " + displayRecords[i].student_country + "</th>");
        console.log(tr);
        $('#emp_body').append(tr);
    }
}

function apply_pagination() {
    $pagination.twbsPagination({
        totalPages: totalPages,
        visiblePages: 6,
        onPageClick: function (event, page) {
            displayRecordsIndex = Math.max(page - 1, 0) * recPerPage;
            endRec = (displayRecordsIndex) + recPerPage;
            displayRecords = records.slice(displayRecordsIndex, endRec);
            generate_table();
        }
    });
}

function replaceAll(str, find, replace) {
    return str.replace(new RegExp(find, 'g'), replace);
}